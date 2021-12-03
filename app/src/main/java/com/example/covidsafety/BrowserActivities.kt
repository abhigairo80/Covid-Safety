package com.example.covidsafety

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View


import androidx.appcompat.app.ActionBarDrawerToggle

import com.google.firebase.auth.FirebaseAuth

import eu.example.covidSafety.R
import eu.example.covidSafety.covidsafety.Scanner
import kotlinx.android.synthetic.main.activity_browseractivities.*


class BrowserActivities : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility=View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.activity_browseractivities)

        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.logoutAction ->{
                        FirebaseAuth.getInstance().signOut()
                        startActivity(Intent(this,RegisterActivity::class.java))
                        finish()
                    }
                R.id.scanner ->{ this.startActivity(Intent(this, Scanner::class.java)) }
            };true

        }

        activecases.setOnClickListener {
            val open = Intent(Intent.ACTION_VIEW,Uri.parse("https://covid19india.org/"))
            startActivity(open) }
        bed_avail.setOnClickListener {
            val open = Intent(Intent.ACTION_VIEW,Uri.parse("https://delhifightscorona.in/data/hospital-beds/"))
            startActivity(open) }
        guidelines.setOnClickListener {
            val open = Intent(Intent.ACTION_VIEW,Uri.parse("https://www.covid19treatmentguidelines.nih.gov/"))
            startActivity(open) }
        mcq_page.setOnClickListener {
            startActivity(Intent(this,QuestionsActivity::class.java))
        }
        preventions.setOnClickListener{
            startActivity(Intent(this,SlidingPages::class.java))
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}