package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var usernameInput :EditText
    lateinit var passwordInput :EditText
    lateinit var loginBtn :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        usernameInput=findViewById(R.id.username_input)
        passwordInput=findViewById(R.id.password_input)
        loginBtn=findViewById(R.id.login_btn)

        loginBtn.setOnClickListener{
            val username=usernameInput.text.toString().trim()

            val email_pattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            if(username.matches(email_pattern.toRegex())){
                Toast.makeText(this, "Valid Email", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "InValid Email", Toast.LENGTH_SHORT).show()
            }
            val password=passwordInput.text.toString()
            Log.i("Test credentials","Username=$username and Password =$password")
        }
    }
}
