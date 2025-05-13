package com.uoc.fragments1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uoc.fragments1.databinding.ActivityAddSeminarBinding

class AddSeminarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddSeminarBinding

    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddSeminarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnNew.setOnClickListener {

            //BEGIN-CODE-UOC-6.4
            val l:AddSeminarResult = AddSeminarResult(binding.editTitle.text.toString(),
                binding.editDuration.text.toString(),
                binding.editLevel.text.toString(),
                "https://www.revolumedia.com/uoc/img/questions1.jpg")
            val intent = Intent()
            intent.putExtra(PARAM_ADDREQUESTRESULT_CLASS, l)
            this.setResult(Activity.RESULT_OK,intent)
            finish()
            //END-CODE-UOC-6.4

        }

    }
}