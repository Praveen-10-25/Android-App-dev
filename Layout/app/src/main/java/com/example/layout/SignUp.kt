package com.example.layout

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignUp : AppCompatActivity() {

    // Declare all input fields and buttons
    private lateinit var useremail: EditText
    private lateinit var userpassword: EditText
    private lateinit var userconform: EditText
    private lateinit var userphone: EditText
    private lateinit var usernameInput: EditText
    private lateinit var usersignup: Button
    private lateinit var returnhome: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        // Bind views to variables
        useremail = findViewById(R.id.emailInput)
        usernameInput = findViewById(R.id.usernameInput)
        userpassword = findViewById(R.id.passwordInput)
        userconform = findViewById(R.id.passwordInput2)
        userphone = findViewById(R.id.editTextPhone)
        usersignup = findViewById(R.id.buttonsignin)
        returnhome = findViewById(R.id.backtologin)

        // Go back to login activity
        returnhome.setOnClickListener {
            val intent = Intent(this@SignUp, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Sign-up button click logic
        usersignup.setOnClickListener {
            val name = usernameInput.text.toString().trim()
            val email = useremail.text.toString().trim()
            val phone = userphone.text.toString().trim()
            val password = userpassword.text.toString().trim()
            val conformpass = userconform.text.toString().trim()

            // Validate fields
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || conformpass.isEmpty()) {
                Toast.makeText(this, "Fill all the Fields.....", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Enter a valid Email ID", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.PHONE.matcher(phone).matches()) {
                Toast.makeText(this, "Enter a valid Mobile Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!passchecker(password)) {
                Toast.makeText(
                    this,
                    "Password must be at least 8 characters with uppercase, lowercase, digit, and special character.",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            if (password != conformpass) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save to SharedPreferences
            val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("username", name)
            editor.putString("email", email)
            editor.putString("password", password)
            editor.putString("phone", phone)
            editor.apply()

            Toast.makeText(this, "Sign-up Successful!", Toast.LENGTH_SHORT).show()

            // Navigate back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Function to validate password
    private fun passchecker(password: String): Boolean {
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }
        return password.length >= 8 && hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar
    }
}
