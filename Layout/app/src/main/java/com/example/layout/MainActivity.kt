package com.example.layout

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var userEmail: EditText
    lateinit var userpassword: EditText
    lateinit var loginbtrn: Button
    lateinit var signupbtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        userEmail = findViewById(R.id.emailInput)
        userpassword = findViewById(R.id.passwordInput)
        loginbtrn = findViewById(R.id.buttonlogin)
        signupbtn=findViewById(R.id.buttonsignup)

        signupbtn.setOnClickListener{
            val signUp= Intent(this@MainActivity,SignUp::class.java)
            startActivity(signUp)
        }

        loginbtrn.setOnClickListener {
            val email = userEmail.text.toString()
            val password = userpassword.text.toString()
            val pattern = android.util.Patterns.EMAIL_ADDRESS

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please enter both Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pattern.matcher(email).matches() && passpat(password)) {
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Enter a valid Email and Strong Password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun passpat(password: String): Boolean {
        val minLength = 8
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }

        return password.length >= minLength &&
                hasDigit &&
                hasLowerCase &&
                hasSpecialChar &&
                hasUpperCase
    }
}
