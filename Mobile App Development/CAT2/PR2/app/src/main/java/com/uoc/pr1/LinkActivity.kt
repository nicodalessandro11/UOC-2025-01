package com.uoc.pr1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.uoc.pr1.databinding.ActivityLinkBinding

// BEGIN-CODE-UOC-7
class LinkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLinkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val link = intent.getStringExtra("link")

        if (link != null && link.isNotEmpty()) {
            binding.html.loadUrl(link)
        }

        // BEGIN-COMMENT-UOC-7f
        // The permission added in the AndroidManifest.xml file to be able to load the web page
        // is <uses-permission android:name="android.permission.INTERNET" />

    }
// END-CODE-UOC-7
}