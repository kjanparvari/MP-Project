package com.example.mp_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.preference.PreferenceManager
import android.util.Log
import com.example.mp_project.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val emailEditText: EditText = findViewById(R.id.loginEmail)
        val passwordEditText: EditText = findViewById(R.id.loginPassword)
        val submitBtn: Button = findViewById(R.id.loginSubmitButton)
        val redirectBtn: Button = findViewById(R.id.LoginRedirectToRegister)
//        passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD

        submitBtn.setOnClickListener {
            val prefs = PreferenceManager.getDefaultSharedPreferences(this)
            val json = prefs.getString("users", "[]").toString()
            val type = object : TypeToken<MutableList<User>>() {}.type
            val users = Gson().fromJson<MutableList<User>>(json, type)
            Log.d("users", json)
            Log.d("all", prefs.all.toString())
            for (u in users) {
                if (emailEditText.text.toString()
                        .trim() == u.email && passwordEditText.text.toString() == u.password
                ) {
                    prefs.edit().putBoolean("isLogin", true).apply()
                    val intent = Intent(this, MainActivity::class.java)
                    this.startActivity(intent)
                }
            }


        }
        redirectBtn.setOnClickListener {
            emailEditText.setText("")
            passwordEditText.setText("")
            val intent = Intent(this, RegisterActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun onBackPressed() {
//        if (shouldAllowBack()) {
//            super.onBackPressed()
//        } else {
//            doSomething()
//        }
        return
    }
}