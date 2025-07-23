package com.example.layout

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
            val email = userEmail.text.toString().trim()
            val password = userpassword.text.toString().trim()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please enter both Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
            val storedEmail = sharedPref.getString("email", null)
            val storedPassword = sharedPref.getString("password", null)

            println("Stored Email: $storedEmail")
            println("Stored Password: $storedPassword")
            println("Entered Email: $email")
            println("Entered Password: $password")

            Log.d("DEBUG", "Stored Password: $storedPassword")
            Log.d("DEBUG", "Input Password: $password")



            if (storedPassword==null || storedEmail==null) {
                Toast.makeText(this, "No account Found.please Sign-Up", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (email == storedEmail && password == storedPassword) {
                    Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Incorrect Mail or Password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
