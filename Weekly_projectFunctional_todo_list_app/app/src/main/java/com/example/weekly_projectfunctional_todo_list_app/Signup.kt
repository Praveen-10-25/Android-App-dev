package com.example.weekly_projectfunctional_todo_list_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.sign

class Signup : AppCompatActivity() {
    private lateinit var username:EditText
    private lateinit var useremail:EditText
    private lateinit var userpassword:EditText
    private lateinit var confirmpassword:EditText
    private lateinit var signingup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)

        username=findViewById(R.id.nameinput)
        useremail=findViewById(R.id.emailInputsignup)
        userpassword=findViewById(R.id.input_Passwordsignup)
        confirmpassword=findViewById(R.id.comfimPassword)
        signingup=findViewById(R.id.btnsigningup)

        signingup.setOnClickListener {
            val usermail=useremail.text.toString().trim()
            val password=userpassword.text.toString().trim()
            val confirmpass=confirmpassword.text.toString().trim()
            val name=username.text.toString().trim()

            if(usermail.isEmpty()||password.isEmpty()||confirmpass.isEmpty()||name.isEmpty()) {
                Toast.makeText(this, "Fill all the Fields.....", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(usermail).matches()) {
                Toast.makeText(this, "Enter a valid Email ID", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!passchecker(password)){
                Toast.makeText(
                    this,
                    "Password must be at least 8 characters with uppercase, lowercase, digit, and special character.",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if(confirmpass!=password){
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val shareddata=getSharedPreferences("Userdata", MODE_PRIVATE)
            val editor=shareddata.edit()
            editor.putString("name",name)
            editor.putString("email",usermail)
            editor.putString("password",password)
            editor.apply()

            val Loginpage=Intent(this@Signup, MainActivity::class.java)
            startActivity(Loginpage)
            finish()
        }
        }
    private fun passchecker(password: String): Boolean {
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }
        return password.length >= 8 && hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar
    }

}
