package com.uoc.pr1

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.uoc.pr1.databinding.ActivityAddSeminarBinding
import java.io.InputStream

class AddSeminarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddSeminarBinding
    private var selectedImageUri: Uri? = null

    //BEGIN-CODE-UOC-6.3

    // 6.3. Listener for the select image button
    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

        // 6.3.1. We get the result back from the image picker
        if (result.resultCode == Activity.RESULT_OK) {
            val uri: Uri? = result.data?.data

            // 6.3.2. We check if the image got a valid location and display it in the ImageView
            if (uri != null) {
                val inputStream: InputStream? = contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                binding.ivImage.setImageBitmap(bitmap)
                selectedImageUri = uri
            }
        }
    }

    //END-CODE-UOC-6.3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSeminarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectImage.setOnClickListener {
            val intent = Intent().apply {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT
            }
            getResult.launch(intent)
        }

        //BEGIN-CODE-UOC-6.4

        // 6.4. Listener for the add seminar button
        binding.btnNew.setOnClickListener {

            val title = binding.editTitle.text.toString()

            // 6.4.1. We create the intent with the result (Modified to use Parcelable object correctly)
            val resultIntent = Intent().apply {
                putExtra(PARAM_ADDREQUESTRESULT_CLASS, AddSeminarResult(title, selectedImageUri))
            }

            // 6.4.2 We set the result and close the activity
            setResult(Activity.RESULT_OK, resultIntent)
            finish()

        }

        //END-CODE-UOC-6.4
    }
}
