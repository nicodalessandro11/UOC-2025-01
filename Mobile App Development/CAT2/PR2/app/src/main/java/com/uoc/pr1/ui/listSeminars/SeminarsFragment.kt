package com.uoc.pr1.ui.list

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.uoc.pr1.MainActivity
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

        // BEGIN-CODE-UOC-3
        binding.newSeminarButton.setOnClickListener {
            Toast.makeText(context, "The 'new seminar' button has been pressed.", Toast.LENGTH_SHORT).show()
        }
        // END-CODE-UOC-3

        recyclerView = binding.recyclerList


        l = LinearLayoutManager((activity as MainActivity?)!!,LinearLayoutManager.VERTICAL,false)



        recyclerView.setLayoutManager(l);



       recyclerView.adapter = seminarsAdapter



        seminarsViewModel.itemsLiveData?.observe(  (activity as MainActivity?)!!, {
            it?.let {

                seminarsAdapter.submitList(it as MutableList<Seminary>)


            }

        })





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



