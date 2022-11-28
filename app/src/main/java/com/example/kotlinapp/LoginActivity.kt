package com.example.kotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btnSignUp= findViewById<Button>(R.id.btnSignUp)
        btnSignUp.setOnClickListener {
            var i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }
    }
}