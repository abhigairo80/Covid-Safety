package com.example.covidsafety
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import eu.example.covidSafety.R
import kotlinx.android.synthetic.main.activity_questions.*


class QuestionsActivity : AppCompatActivity(),View.OnClickListener {

    private var mCurrentposition:Int=1
    private var mQuestionsList:ArrayList<questions>?=null
    private var mSelectedOptionPosition: Int =0
    private var TotalScore:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

         mQuestionsList=Constants.getQuestions()
        setQuestion()
        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        button.setOnClickListener(this)

    }
    @SuppressLint("SetTextI18n")
    private fun setQuestion(){
        val Question = mQuestionsList!![mCurrentposition-1]
        defaultOptionsView()
        progressBar.progress=mCurrentposition
        tv_progress.text= "$mCurrentposition/12"
        tv_question.text=Question.question
        option1.text = Question.option1
        option2.text = Question.option2
        if(mCurrentposition==mQuestionsList!!.size){
            button.text="SUBMIT"
        }
        else{
            button.text="SELECT"
        }

    }

    override fun onClick(v:View?){

           when (v?.id) {
               R.id.option1 -> {
                   selectedOptionView(option1, 1)
               }
               R.id.option2 -> {
                   selectedOptionView(option2, 2)
               }

               R.id.button -> {
                   if (mSelectedOptionPosition == 0) {
                       mCurrentposition++
                       when {
                           mCurrentposition <= mQuestionsList!!.size -> {
                               setQuestion()
                           }
                           else -> {
                               Toast.makeText(
                                   this,
                                   "Your Response has been Successfully Submitted",
                                   Toast.LENGTH_SHORT
                               ).show()
                               val intent = Intent(this, ResultActivity::class.java)
                               intent.putExtra(Constants.TOTAL_QUESTION,mQuestionsList!!.size)
                               intent.putExtra(Constants.TOTAL_SCORE,TotalScore)
                               startActivity(intent)
                               finish()
                           }
                       }
                   }
                   else{
                       val question=mQuestionsList?.get(mCurrentposition-1)
                       if(mSelectedOptionPosition==mSelectedOptionPosition){
                           answerView(mSelectedOptionPosition,R.drawable.roundoption)
                       }
                       if(mSelectedOptionPosition==1){
                           TotalScore += 1
                       }
                       if(mSelectedOptionPosition==2){
                           TotalScore += 0
                       }

                       if(mCurrentposition==mQuestionsList!!.size){
                           button.text="CONFIRM SUBMIT"
                       }
                       else{
                           button.text="NEXT"
                       }
                       mSelectedOptionPosition=0

                   }
               }
           }

    }

    private fun defaultOptionsView(){
        val options=ArrayList<TextView>()
        options.add(0,option1)
        options.add(1,option2)
    for(option in options){
        option.setTextColor(Color.parseColor("#ED0909"))
        option.typeface= Typeface.DEFAULT
        option.background=ContextCompat.getDrawable(
            this,
            R.drawable.rectangleoption
        )
    }
    }

    private fun selectedOptionView(tv: TextView,selectedOptionNum:Int){
        defaultOptionsView()
        mSelectedOptionPosition=selectedOptionNum
        tv.setTextColor(Color.parseColor("#ED0909"))
        tv.background=ContextCompat.getDrawable(this,R.drawable.highlightedoption)
    }
    private fun answerView(answer:Int,drawableView:Int){
        when(answer){
            1->{
                option1.background=ContextCompat.getDrawable(this,drawableView)
            }2->{
            option2.background=ContextCompat.getDrawable(this,drawableView)
            }
        }
    }
}