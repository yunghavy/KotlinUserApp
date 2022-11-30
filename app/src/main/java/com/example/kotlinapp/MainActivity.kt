package com.example.kotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userName = findViewById<EditText>(R.id.username)
        var userEmail = findViewById<EditText>(R.id.email)
        var userPhone = findViewById<EditText>(R.id.phone)
        var userPassword = findViewById<EditText>(R.id.password)
        var confirmPass = findViewById<EditText>(R.id.confirm)


        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val i =Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)

        }
        var progressBar = findViewById<ProgressBar>(R.id.progress)
        progressBar.visibility = View.GONE

        var signUpBtn = findViewById<Button>(R.id.signUpBtn)
        signUpBtn.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            var payload = JSONObject()
            payload.put("user", userName.text.toString())
            payload.put("email", userEmail.text.toString())
            payload.put("phone", userPhone.text.toString())
            payload.put("password", userPassword.text.toString())
            payload.put("confirm", confirmPass.text.toString())

            var conBody = StringEntity(payload.toString())

            var client = AsyncHttpClient(true, 80, 443)
            client.post(this, "https://ghostsec.pythonanywhere.com/register",conBody,
                "application/json",
                object : JsonHttpResponseHandler(){

                    // onSuccess
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        response: JSONObject?
                    ) {

                       when(statusCode) {

                           10 -> Toast.makeText(applicationContext, "Invalid Email", Toast.LENGTH_SHORT).show()
                           20 -> Toast.makeText(applicationContext, "Invalid username", Toast.LENGTH_SHORT).show()
                           30 -> Toast.makeText(applicationContext, "Have atleast 8 characters password", Toast.LENGTH_SHORT).show()
                           40 -> Toast.makeText(applicationContext, "Passwords dont match!", Toast.LENGTH_SHORT).show()

                           200 -> {
                               Toast.makeText(
                                   applicationContext,
                                   "Saved Successfully",
                                   Toast.LENGTH_SHORT
                               ).show()
                               var i = Intent(applicationContext, LoginActivity::class.java)
                               startActivity(i)
                               finish()
                           }
                           }

                       }

                    }

                } // end Handler



            ) // end client

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
// consuming APIs in Android -> loopj, Retrofit, fuel
// loopj library
// Dependency -> resource to be used by the android application
// Gradle -> manage dependencies in android