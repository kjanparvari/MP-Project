package com.example.mp_project

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.preference.PreferenceManager
import android.text.InputType


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
            if (emailEditText.text.toString() == "admin@admin.com" && passwordEditText.text.toString() == "123456") {
                val prefs = PreferenceManager.getDefaultSharedPreferences(this)
                prefs.edit().putBoolean("Islogin", true).apply()
                val intent = Intent(this, MainActivity::class.java)
                this.startActivity(intent)
            }
        }
        redirectBtn.setOnClickListener{
            emailEditText.setText("")
            passwordEditText.setText("")
            val intent = Intent(this, RegisterActivity::class.java)
            this.startActivity(intent)
        }
    }
}