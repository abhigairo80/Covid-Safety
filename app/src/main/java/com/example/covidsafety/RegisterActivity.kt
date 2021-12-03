package com.example.covidsafety
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import eu.example.covidSafety.R
import eu.example.covidSafety.covidsafety.BaseActivity
import eu.example.covidSafety.covidsafety.LoginActivity
import eu.example.myapplication.firebase.FirestoreClass
import eu.example.myapplication.model.User
import kotlinx.android.synthetic.main.activity_main.*

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_main)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        login.setOnClickListener {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        signup_button.setOnClickListener {
            if(person_name.text.toString().isEmpty()){
                Toast.makeText(this@RegisterActivity,"PLEASE ENTER NAME",Toast.LENGTH_SHORT).show()
            }
            else if(person_mail.text.toString().isEmpty()){
                Toast.makeText(this@RegisterActivity,"PLEASE ENTER EMAIL",Toast.LENGTH_SHORT).show()
            }
            else if(person_password.text.toString().isEmpty()){
                Toast.makeText(this@RegisterActivity,"PLEASE ENTER PASSWORD",Toast.LENGTH_SHORT).show()
            }
            else if(person_password.text.toString().length<6)
            {
                Toast.makeText(this,"Password should have atleast 6 characters",Toast.LENGTH_SHORT).show()
            }
            else if(person_age.text.toString().isEmpty()){
                Toast.makeText(this@RegisterActivity,"PLEASE ENTER AGE",Toast.LENGTH_SHORT).show()
            }

            else{

                registerUser()
            }
        }

    }

    private fun registerUser() {
        // Here we get the text from editText and trim the space
        val name: String = person_name.text.toString().trim { it <= ' ' }
        val email: String = person_mail.text.toString().trim { it <= ' ' }
        val password: String = person_password.text.toString().trim { it <= ' ' }

        if (validateForm(name, email, password)) {
            // Show the progress dialog.
            showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->

                        if (task.isSuccessful) {

                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            val registeredEmail = firebaseUser.email!!
                            val user = User(
                                firebaseUser.uid, name, registeredEmail
                            )

                            FirestoreClass().registerUser(this, user)
                        } else {
                            Toast.makeText(
                                this,
                                task.exception!!.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
        }
    }
    private fun validateForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("Please enter name.")
                false
            }
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("Please enter email.")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("Please enter password.")
                false
            }
            else -> {
                true
            }
        }
    }



    fun userRegisteredSuccess() {
        Toast.makeText(
            this,
            "You have successfully registered.",
            Toast.LENGTH_SHORT
        ).show()
        hideProgressDialog()
        FirebaseAuth.getInstance().signOut()
        // Finish the Sign-Up Screen
        finish()
    }

}



