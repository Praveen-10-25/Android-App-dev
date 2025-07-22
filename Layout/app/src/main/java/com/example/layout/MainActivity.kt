package com.example.layout

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var userEmail: EditText
    lateinit var userpassword: EditText
    lateinit var loginbtrn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        userEmail = findViewById(R.id.Email_input)
        userpassword = findViewById(R.id.Password_input)
        loginbtrn = findViewById(R.id.button)

        loginbtrn.setOnClickListener {
            val email = userEmail.toString().trim()
            val password = userpassword.text.toString()
            val pattern = "[a-zA-z0-9._-]+\\.+[a-z]+"
            if(email!=null&&password!=null){
                if (email.matches(pattern.toRegex())&&!passpat(password)){
                    Toast.makeText(this,"Login SuccessFully",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Enter an Valid Email and Password",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }else{
                Toast.makeText(this,"You can't Login Without Entering Your Email and Password.......",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
    }
    private fun passpat(password:String):Boolean{
        val min_length=8
        val hasUpperCase=password.any{it.isUpperCase()}
        val hasLowerCase=password.any { it.isLowerCase() }
        val hasdigit=password.any{it.isDigit()}
        val hasSpecialChar=password.any{!it.isLetterOrDigit()}
        return password.length>=min_length
                &&hasdigit
                &&hasLowerCase
                &&hasSpecialChar
                &&hasUpperCase
    }

}