package com.example.covidsafety

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import eu.example.covidSafety.R
import eu.example.covidSafety.covidsafety.ScrollPageAdapter
import kotlinx.android.synthetic.main.activity_slidingpages.*

import me.relex.circleindicator.CircleIndicator3

class SlidingPages : AppCompatActivity() {
    private var imagesList= mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slidingpages)
        postToList()
            view_pager2.adapter=ScrollPageAdapter(imagesList)
        view_pager2.orientation= ViewPager2.ORIENTATION_HORIZONTAL
        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        indicator.setViewPager(view_pager2)
    }
    private fun addToList(images:Int){
        imagesList.add(images)
    }
    private fun postToList(){
        addToList(R.drawable.homecare)
        addToList(R.drawable.ill)
        addToList(R.drawable.workspace)
        addToList(R.drawable.mask)
        addToList(R.drawable.cholorine)
        addToList(R.drawable.travelling)
        addToList(R.drawable.mosquitoes)
        addToList(R.drawable.hand)
        addToList(R.drawable.protect)
        addToList(R.drawable.antibiotics)
    }
}