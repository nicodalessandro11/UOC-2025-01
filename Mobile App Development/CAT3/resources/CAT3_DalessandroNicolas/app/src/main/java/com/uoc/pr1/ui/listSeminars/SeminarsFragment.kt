package com.uoc.pr1.ui.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uoc.pr1.AddSeminarActivity
import com.uoc.pr1.AddSeminarResult

import com.uoc.pr1.MainActivity
import com.uoc.pr1.PARAM_ADDREQUESTRESULT_CLASS
import com.uoc.pr1.data.DataSource
import com.uoc.pr1.data.model.Seminary
import com.uoc.pr1.databinding.FragmentListSeminarsBinding


class SeminarsFragment : Fragment() {
    private lateinit var binding: FragmentListSeminarsBinding

    private val seminarsViewModel by viewModels<SeminarsViewModel> {
        SeminarsViewModelViewModelFactory(requireContext())
    }


    lateinit var recyclerView: RecyclerView
    lateinit var seminarsAdapter:  SeminarsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }



    lateinit var l:LinearLayoutManager

    lateinit var timer:CountDownTimer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //
        binding = FragmentListSeminarsBinding.inflate(inflater)


        seminarsAdapter = SeminarsAdapter { d -> adapterOnClick(d) }



        recyclerView = binding.recyclerList


        l = LinearLayoutManager((activity as MainActivity?)!!,LinearLayoutManager.VERTICAL,false)



        recyclerView.setLayoutManager(l);



        recyclerView.adapter = seminarsAdapter



        seminarsViewModel.itemsLiveData?.observe(  (activity as MainActivity?)!!, {
            it?.let {

                seminarsAdapter.submitList(it as MutableList<Seminary>)


            }

        })

        var getResult  = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK){
                val value = it.data?.getParcelableExtra<AddSeminarResult>(
                    PARAM_ADDREQUESTRESULT_CLASS
                )

                //BEGIN-CODE-UOC-6.5

                // 6.5.1 As requested in the statement we use ? to safety access properties tha can be null

                val dataSource = DataSource.getDataSource(DataSource.DataSourceFactory.Default)
                dataSource.addSeminary(value!!.title, value!!.uri)

                //END-CODE-UOC-6.5


            }
        }


        binding.btnNew.setOnClickListener {
            //Toast.makeText(activity, "The 'New seminar' button has been pressed.", Toast.LENGTH_SHORT).show()

            //BEGIN-CODE-UOC-6.2

            // 6.2.1. We create the intent to open the Add Seminar view
            val intent = Intent(this.context, AddSeminarActivity::class.java)
            // 6.2.2. We launch the activity so we can get the results when it ends
            getResult.launch(intent)

            //END-CODE-UOC-6.2
        }

        return binding.root
    }



    override fun onDestroy()
    {
        super.onDestroy()

    }

    override fun onResume()
    {
        super.onResume()


    }

    private fun adapterOnClick(item: Seminary) {
        // (activity as MainActivity?)!!.openSeminary(item)
        (activity as MainActivity?)!!.openQuiz(item)

    }

    fun SetAdapter()
    {

        recyclerView.adapter = seminarsAdapter
    }

    companion object {


        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            SeminarsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}



