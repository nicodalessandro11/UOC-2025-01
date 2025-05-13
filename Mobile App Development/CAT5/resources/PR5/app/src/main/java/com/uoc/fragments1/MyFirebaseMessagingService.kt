package com.uoc.fragments1

import android.app.AlertDialog
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title ?: "No title"
        val body = remoteMessage.notification?.body ?: "No body"

        // BEGIN-CODE-UOC-CAT5-3A
        // Reproducing the error AlertDialog directly from background thread
        /*
        val builder = AlertDialog.Builder(MainActivity.gmainActivity)
        builder.setTitle(title)
        builder.setMessage(body)
        builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        builder.show()
        */
        // END-CODE-UOC-CAT5-3A

        // BEGIN-CODE-UOC-CAT5-3B
        // Why this fails:
        // ERROR: CalledFromWrongThreadException.
        // Android not allow updating the UI from background threads.
        // The onMessageReceived is being executed in a background thread,
        // and AlertDialogs must be created on the main thread.
        // This is doing to crash the app.
        // END-CODE-UOC-CAT5-3B

        // BEGIN-CODE-UOC-CAT5-3C
        // Correct using coroutine on main thread as explained in the wiki
        GlobalScope.launch(Dispatchers.Main) {
            val builder = AlertDialog.Builder(MainActivity.gmainActivity)
            builder.setTitle(title)
            builder.setMessage(body)
            builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss()   }

            builder.show()
        }
    }
    // END-CODE-UOC-CAT5-3C

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("Messages", "New FCM token: $token")
    }

}
