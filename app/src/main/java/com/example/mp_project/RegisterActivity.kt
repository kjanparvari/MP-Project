package com.example.mp_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val emailEditText: EditText = findViewById(R.id.registerEmail)
        val passwordEditText: EditText = findViewById(R.id.registerPassword)
        val passwordConfirmEditText: EditText = findViewById(R.id.registerPasswordRepeat)
        val submitBtn: Button = findViewById(R.id.registerSubmitButton)
        val redirectBtn: Button = findViewById(R.id.registerRedirectToLogin)
    }
}