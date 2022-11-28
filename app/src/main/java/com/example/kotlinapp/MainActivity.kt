package com.example.kotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            var i =Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)

        }


    }
}


// signup page, login screen
/* text view -> signup
4 edit text -> username, email, phone and password
button -> register

Already registered? Login

-< Login
TextView -> Login
2 Edit text -> email, password
button -> login

Not registered yet? Sign up

Drawables -> design of a component

 */