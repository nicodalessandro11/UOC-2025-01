package com.uoc.fragments1

import android.Manifest
import android.animation.Animator
import android.animation.ObjectAnimator
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.ChangeTransform
import android.transition.TransitionSet
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.uoc.fragments1.data.DataSource
import com.uoc.fragments1.data.ListenerData
import com.uoc.fragments1.data.model.Seminary
import com.uoc.fragments1.databinding.ActivityMainBinding
import com.uoc.fragments1.ui.list.SeminarsFragment
import com.uoc.fragments1.ui.login.LoginFragment
import com.uoc.fragments1.ui.login.LoginViewModel
import com.uoc.fragments1.ui.login.LoginViewModelFactory
import com.uoc.fragments1.ui.quiz.QuizFragment

class DetailsTransition : TransitionSet() {
    init {
        setOrdering(ORDERING_TOGETHER)
        addTransition(ChangeBounds())
        addTransition(ChangeTransform())
        addTransition(ChangeImageTransform())
    }
}

class MainActivity : AppCompatActivity(), LoginFragment.OnFragmentLoginInteractionListener {

    companion object {
        public lateinit var gmainActivity: MainActivity
    }

    lateinit var dataSource: DataSource

    private val sharedViewModel: LoginViewModel by
    viewModels<LoginViewModel>() { LoginViewModelFactory() }

    lateinit var f1: LoginFragment

    var seminars_fragment: SeminarsFragment? = null
    private lateinit var binding: ActivityMainBinding

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        gmainActivity = this
        FirebaseApp.initializeApp(this)

        dataSource = DataSource.getDataSource(DataSource.DataSourceFactory.Default, this)

        //    dataSource.selectItemsSeminary(0)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        f1 = LoginFragment()
        f1.loginViewModel = sharedViewModel

        supportFragmentManager.commit {
            //     setReorderingAllowed(true)
            add(R.id.placeholder1, f1)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }

        // ***********************************************************
        // BEGIN FIREBASE CODE
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Messages", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            val token = task.result
            Log.d("Messages", "FCM Token: $token")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val quizChannelId = "quiz"
            val quizChannelName = "Quiz Notifications"
            val quizChannelDescription = "Channel for quiz-related notifications"
            val importance = NotificationManager.IMPORTANCE_HIGH

            val quizChannel =
                NotificationChannel(quizChannelId, quizChannelName, importance).apply {
                    description = quizChannelDescription
                }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(quizChannel)
        }

        if (intent?.extras != null) {
            for (key in intent.extras!!.keySet()) {
                val value = intent.extras!!.getString(key)
                Log.d("Messages", "Key: $key Value: $value")

                if (key == "msg" && value != null) {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Notification")
                    builder.setMessage(value)
                    builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
                    builder.show()
                }
            }
        }
        // END FIREBASE CODE
        // ***********************************************************


        // BEGIN-GEOLOCATION-1

        if(isPermissionsGranted()){
                    GetLocation()
                } else {
                    requestLocationPermission()
                }

        // END-GEOLOCATION-1

        // **********************************************************
    }

    // ***************************************************************
    // Your code related to the request Location Permission code
    // ***************************************************************

    private fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Go to settings", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            0 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                GetLocation()
            } else {
                Toast.makeText(
                    this,
                    "To activate the location go to settings and accept the permissions",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }


    fun GetLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            // BEGIN-GEOLOCATION-2
            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                null
            ).addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    Log.d("Location", "Latitude: $latitude, Longitude: $longitude")
                } else {
                    Log.d("Location", "Location is null. Try again.")
                }
            }.addOnFailureListener { e ->
                Log.e("LocationError", "Failed to get location", e)
            }
            // END-GEOLOCATION-2

            // BEGIN-GEOLOCATION-3
            /*
             location.distanceTo will give the straight line distance in meters.
             It not indicates the actual walking distance to the point.
             It is useful for basic estimation in navigation, but if we want
             the walking distance calculation, we will need the layout of the streets.
            */
            // END-GEOLOCATION-3

            // BEGIN-GEOLOCATION-4
            /*
             To get the accurate layout for calculating the walking distance, we need to
             use a routing API like Google Maps Directions API that supports the walking mode.
             This API considers streets and constrains needed for walking distance calculations.
             As the statement mentions, it is a paid service and requires billing enabled
             in Google Cloud.
            */
            // END-GEOLOCATION-4
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            f1.Reset()
        }

        super.onBackPressed()
    }

    public fun ShowAlertResult(correct: Int, count: Int) {
        val builder = AlertDialog.Builder(this)

        with(builder) {
            setTitle("Result")
            setMessage("Correct: $correct / Total: $count")
            setPositiveButton(
                "OK",
                DialogInterface.OnClickListener({ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                })
            )

            show()
        }

        if (correct == count) {
            Animate()
        }
    }

    fun Animate() {
        val animatedImageView = findViewById<ImageView>(R.id.animatedImageView)
        val screenHeight = resources.displayMetrics.heightPixels
        val screenWidth = resources.displayMetrics.widthPixels

        val layoutParams = animatedImageView.layoutParams
        layoutParams.width = screenWidth // Width in pixels
        layoutParams.height = screenWidth // Height in pixels
        animatedImageView.layoutParams = layoutParams

        // Vertical translation animation (moving bottom to top)
        val translationY =
            ObjectAnimator.ofFloat(
                animatedImageView,
                "translationY",
                screenHeight.toFloat(),
                -(screenHeight + 300).toFloat()
            )
        translationY.setDuration(3000)

        translationY.addListener(
            object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    // No action needed when animation starts
                }

                override fun onAnimationEnd(animation: Animator) {
                    // Hide the ImageView when animation ends
                    animatedImageView.visibility = View.INVISIBLE
                }

                override fun onAnimationCancel(animation: Animator) {
                    // No action needed if animation is canceled
                }

                override fun onAnimationRepeat(animation: Animator) {
                    // No action needed when animation repeats
                }
            }
        )

        translationY.start()

        animatedImageView.visibility = ImageView.VISIBLE
    }

    fun returnFromQuiz(wow: Boolean, correct: Int, count: Int) {
        supportFragmentManager.popBackStack()
        ShowAlertResult(correct, count)
    }

    fun OpenLink(link: String) {
        val intent = Intent(this, LinkActivity::class.java).apply { putExtra("link", link) }

        startActivity(intent)
    }

    fun openQuiz(d: Seminary) {
        var f3: Fragment

        f3 = QuizFragment.newInstance("", "")
        f3.item = d

        f3.setSharedElementEnterTransition(DetailsTransition())
        f3.setSharedElementReturnTransition(DetailsTransition())

        /*
                listener.onItemsSeminar = {
                    supportFragmentManager.commit {
                        addSharedElement(d.view!!, "itemImage")
                        replace(R.id.placeholder1, f3!!)
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        addToBackStack(null)


                    }
                }

                dataSource.selectItemsSeminary(d.id,listener)
        */
        dataSource.selectItemsSeminary(d.id)

        supportFragmentManager.commit {
            addSharedElement(d.view!!, "itemImage")
            replace(R.id.placeholder1, f3!!)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            addToBackStack(null)
        }

        //  ------------------------------------------------

    }

    var listener = ListenerData()
    fun LoginOK(userId: Int) {

        dataSource.selectSeminarssUser(userId)
        LoginOK2()
    }

    fun LoginOK2() {

        Log.d("TAG", "value")

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager

        imm?.hideSoftInputFromWindow(binding.root.windowToken, 0)
        // pop login fragment
        // push list fragment

        if (seminars_fragment == null) {
            seminars_fragment = SeminarsFragment.Companion.newInstance()
        }

        supportFragmentManager.commit {
            replace(R.id.placeholder1, seminars_fragment!!)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            addToBackStack(null)
        }
    }

    override fun onFragmentLoginInteraction(mensaje: String) {
        Log.d("Debug", mensaje)
    }
}
