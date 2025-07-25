package com.example.weekly_projectfunctional_todo_list_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    lateinit var signupbtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        userEmail = findViewById(R.id.emailInput)
        userpassword = findViewById(R.id.input_Password)
        loginbtrn = findViewById(R.id.btnlogin)
        signupbtn=findViewById(R.id.btnsignup)

        signupbtn.setOnClickListener {
            val signup= Intent(this@MainActivity,Signup::class.java)
            startActivity(signup)
        }

        loginbtrn.setOnClickListener {
            val email=userEmail.text.toString().trim()
            val password=userpassword.text.toString().trim()

            if(email.isBlank()||password.isBlank()){
                Toast.makeText(this, "Please enter both Email and Password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sharedata=getSharedPreferences("Userdata", MODE_PRIVATE)
            val storedmail=sharedata.getString("email",null)
            val storedpass=sharedata.getString("password",null)

            Log.d("DEBUG", "Stored Password: $storedpass")
            Log.d("DEBUG", "Input Password: $password")

            if(storedpass==null||storedmail==null){
                Toast.makeText(this, "No account Found.please Sign-Up", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(email==storedmail && password==storedpass){
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                val home=Intent(this@MainActivity,Todopage::class.java)
                startActivity(home)
            } else {
                Toast.makeText(this, "Incorrect Mail or Password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}