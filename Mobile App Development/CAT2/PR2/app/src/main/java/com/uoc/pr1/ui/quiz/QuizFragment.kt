package com.uoc.pr1.ui.quiz

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.uoc.pr1.MainActivity
import com.uoc.pr1.R
import com.uoc.pr1.data.DataSource
import com.uoc.pr1.data.model.Item
import com.uoc.pr1.data.model.Seminary
import androidx.core.graphics.toColorInt

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment() {

    val dataSource: DataSource = DataSource.getDataSource(DataSource.DataSourceFactory.Default)
    private var answers: Array<Int>? = null
    private var current_question: Int = 0
    private var fragmentView:View? = null

    private var question: TextView? = null
    private var answer1: TextView? = null
    private var answer2: TextView? = null
    private var answer3: TextView? = null
    private var answer4: TextView? = null
    private var link: TextView? = null
    private var nextButton: Button? = null
    private var prevButton: Button? = null
    private var finishButton: Button? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    public var item: Seminary? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // BEGIN-CODE-UOC-5c
    public fun LoadValuesCurrentQuestion() {
        // Access to the current element
        val item: Item = dataSource.getItemPos(current_question)

        // Fill in the fields with the current values
        question?.text = item.question
        answer1?.text = item.answer1 ?: ""
        answer2?.text = item.answer2 ?: ""
        answer3?.text = item.answer3 ?: ""
        answer4?.text = item.answer4 ?: ""

        // Background color for all the answers
        answer1?.setBackgroundColor("#EEEEEE".toColorInt())
        answer2?.setBackgroundColor("#EEEEEE".toColorInt())
        answer3?.setBackgroundColor("#EEEEEE".toColorInt())
        answer4?.setBackgroundColor("#EEEEEE".toColorInt())

        // Show the answer if there is one selectec
        if (answers!![current_question] != -1) {
            val selectedAnswer = when (answers!![current_question]) {
                1 -> answer1
                2 -> answer2
                3 -> answer3
                4 -> answer4
                else -> null
            }
            selectedAnswer?.setBackgroundColor(Color.GREEN)
        }
    }
    // END-CODE-UOC-5c

    // BEGIN-CODE-UOC-5d
    fun ClickAnswerLogic(pos: Int) {
        // If the position changes, this logic change the color of the previous answer
        if (pos != answers!![current_question]) {
            val previousAnswer = when (answers!![current_question]) {
                1 -> answer1
                2 -> answer2
                3 -> answer3
                4 -> answer4
                else -> null
            }
            previousAnswer?.setBackgroundColor("#EEEEEE".toColorInt())
        }

        // Update with the new position
        answers!![current_question] = pos

        // Change the color of the new answer
        val newAnswer = when (pos) {
            1 -> answer1
            2 -> answer2
            3 -> answer3
            4 -> answer4
            else -> null
        }
        newAnswer?.setBackgroundColor(Color.GREEN)
    }
    // END-CODE-UOC-5d

    // BEGIN-CODE-UOC-5b
    public fun ButtonsViewLogic() {
        // Hide all the buttons at the beginning
        prevButton?.visibility = View.INVISIBLE
        nextButton?.visibility = View.INVISIBLE
        finishButton?.visibility = View.INVISIBLE
        link?.visibility = View.INVISIBLE

        // Logic to show the buttons
        if (current_question > 0) {
            prevButton?.visibility = View.VISIBLE
        }

        val totalQuestions = dataSource.getCountItems()

        if (current_question < totalQuestions - 1) {
            nextButton?.visibility = View.VISIBLE
        } else {
            finishButton?.visibility = View.VISIBLE
        }

        // Show the link if the question have a link
        val item: Item = dataSource.getItemPos(current_question)
        if (item.link != null && item.link != "") {
            link?.visibility = View.VISIBLE
        }
    }
    // END-CODE-UOC-5b

    // BEGIN-CODE-UOC-6a
    public fun Finish() {
        val correct: Int = dataSource.getCorrects(answers!!)

        (activity as MainActivity?)?.returnFromQuiz(
            false,
            correct,
            dataSource.getCountItems()
        )
    }
    // END-CODE-UOC-6a

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v:View = inflater.inflate(R.layout.fragment_quiz, container, false)
        fragmentView = v

        var v2: ImageView = v.findViewById(R.id.item_image_detail)
        v2.setImageResource(item!!.image!!)

        // BEGIN-CODE-UOC
        question = fragmentView!!.findViewById(R.id.item_text_title)
        answer1 = fragmentView!!.findViewById(R.id.answer1)
        answer2 = fragmentView!!.findViewById(R.id.answer2)
        answer3 = fragmentView!!.findViewById(R.id.answer3)
        answer4 = fragmentView!!.findViewById(R.id.answer4)
        link = fragmentView!!.findViewById(R.id.link)
        nextButton = fragmentView!!.findViewById(R.id.next)
        prevButton = fragmentView!!.findViewById(R.id.prev)
        finishButton = fragmentView!!.findViewById(R.id.finish)

        // Load quiz questions for this seminar
        dataSource.selectItemsSeminary(item!!.id)

        // Initialize the answers property
        answers = Array(dataSource.getCountItems()) { -1 }

        // Create empty listeners for all the elements
        answer1!!.setOnClickListener { ClickAnswerLogic(1) }
        answer2!!.setOnClickListener { ClickAnswerLogic(2) }
        answer3!!.setOnClickListener { ClickAnswerLogic(3) }
        answer4!!.setOnClickListener { ClickAnswerLogic(4) }
        link!!.setOnClickListener {
            val currentItem = dataSource.getItemPos(current_question)
            (activity as MainActivity?)?.OpenLink(currentItem.link ?: "")
        }
        nextButton!!.setOnClickListener {
            current_question++
            ButtonsViewLogic()
            LoadValuesCurrentQuestion()
        }
        prevButton!!.setOnClickListener {
            current_question--
            ButtonsViewLogic()
            LoadValuesCurrentQuestion()
        }
        finishButton!!.setOnClickListener {
            Finish()
        }
        // END-CODE-UOC

        ButtonsViewLogic()
        LoadValuesCurrentQuestion()

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QuizFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}