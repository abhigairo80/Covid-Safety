package eu.example.covidSafety.covidsafety

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.covidsafety.BrowserActivities
import com.example.covidsafety.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import eu.example.covidSafety.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth = FirebaseAuth.getInstance()
        signup_link.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
        login_button.setOnClickListener {
            when {
                login_email.text.toString().isEmpty() -> {
                    Toast.makeText(this,"PLEASE ENTER EMAIL",Toast.LENGTH_SHORT).show()
                }
                login_password.text.toString().isEmpty() -> {
                    Toast.makeText(this,"PLEASE ENTER PASSWORD",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    login()
                }
            }
        }
    }

    private fun login() {
        val email: String = login_email.text.toString()
        val password: String = login_password.text.toString()
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Welcome back.", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, BrowserActivities::class.java))
                finish()
            } else {
                Toast.makeText(this, "Wrong id or password", Toast.LENGTH_SHORT).show()
            }
        }

    }
}