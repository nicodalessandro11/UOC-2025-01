package com.uoc.pr1

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.ChangeTransform
import android.transition.TransitionSet
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.uoc.pr1.databinding.ActivityMainBinding
import com.uoc.pr1.data.DataSource
import com.uoc.pr1.data.model.Seminary
import com.uoc.pr1.data.model.Item
import com.uoc.pr1.ui.quiz.QuizFragment

import com.uoc.pr1.ui.list.SeminarsFragment
import com.uoc.pr1.ui.login.LoginFragment
import com.uoc.pr1.ui.login.LoginViewModel
import com.uoc.pr1.ui.login.LoginViewModelFactory

class DetailsTransition : TransitionSet() {
    init {
        setOrdering(ORDERING_TOGETHER)
        addTransition(ChangeBounds())
        addTransition(ChangeTransform())
        addTransition(ChangeImageTransform())
    }
}


class MainActivity : AppCompatActivity(), LoginFragment.OnFragmentLoginInteractionListener {


    val dataSource: DataSource = DataSource.getDataSource(DataSource.DataSourceFactory.Default)

    private val sharedViewModel: LoginViewModel by viewModels<LoginViewModel>() {
        LoginViewModelFactory()
    }

    lateinit var f1: LoginFragment

    var seminars_fragment: SeminarsFragment? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        f1 = LoginFragment()
        f1.loginViewModel = sharedViewModel





        supportFragmentManager.commit {
        //     setReorderingAllowed(true)
            add(R.id.placeholder1, f1)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)


        }




    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 1)
        {
            f1.Reset()


        }


        super.onBackPressed()
    }



    // BEGIN-CODE-UOC-6b
    fun returnFromQuiz(wow: Boolean, correct: Int, count: Int) {
        supportFragmentManager.popBackStack()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Result")
        builder.setMessage("Correct: $correct / Total: $count")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
    // END-CODE-UOC-6b

    // BEGIN-CODE-UOC-7c
    fun OpenLink(link: String) {
        val intent = Intent(this, LinkActivity::class.java)
        intent.putExtra("link", link)
        startActivity(intent)
    }
    // END-CODE-UOC-7c

    fun openQuiz(d: Seminary)
    {
        var f3: Fragment

        f3 = QuizFragment.newInstance("", "")
        f3.item = d


        f3.setSharedElementEnterTransition(DetailsTransition())
        f3.setSharedElementReturnTransition(DetailsTransition())

        supportFragmentManager.commit {
            addSharedElement(d.view!!, "itemImage")
            replace(R.id.placeholder1, f3!!)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            addToBackStack(null)

        }

    }



    fun LoginOK(userId:Int)
    {

        dataSource.selectSeminarssUser(userId)

        Log.d("TAG","value")

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager


        imm?.hideSoftInputFromWindow(binding.root.windowToken, 0)
        // pop login fragment
        // push list fragment


        if(seminars_fragment==null) {
            seminars_fragment = SeminarsFragment.Companion.newInstance()
        }


       supportFragmentManager.commit {

              replace(R.id.placeholder1, seminars_fragment!!)
              setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
              addToBackStack(null)


        }




    }

    override fun onFragmentLoginInteraction(mensaje: String) {
        Log.d("Debug",mensaje)

    }


}