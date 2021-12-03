package com.example.covidsafety

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import eu.example.covidSafety.R

class SplashScreen : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private val splash : Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        firebaseAuth = FirebaseAuth.getInstance()
        if(firebaseAuth.currentUser!=null)
        {
            Handler().postDelayed({
            startActivity(Intent(this,BrowserActivities::class.java))
            finish()
            }, splash)
        }
        else {
            Handler().postDelayed({
                startActivity(Intent(this, IntroActivity::class.java))
                finish()
            }, splash)
        }
    }
}