package com.example.mp_project

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.example.mp_project.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.LocalDateTime
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val emailEditText: EditText = findViewById(R.id.registerEmail)
        val passwordEditText: EditText = findViewById(R.id.registerPassword)
        val passwordConfirmEditText: EditText = findViewById(R.id.registerPasswordRepeat)
        val submitBtn: Button = findViewById(R.id.registerSubmitButton)
        val redirectBtn: Button = findViewById(R.id.registerRedirectToLogin)

        emailEditText.doAfterTextChanged {
            val isValid: Boolean = it.toString().isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(
                it.toString()
            ).matches()
            emailEditText.error = if (isValid) null else "لطفا یک ایمیل معتبر وارد کنید"
        }
        passwordEditText.doAfterTextChanged {
            if (!Pattern.compile("^(?=.*\\d).{1,20}$").matcher(it.toString()).matches()) {
                passwordEditText.error = "رمز عبور باید شامل حداقل یک عدد باشد"
            } else if (!Pattern.compile("^(?=.*(?=.*[A-Z])).{1,20}$").matcher(it.toString())
                    .matches()
            ) {
                passwordEditText.error = "رمز عبور باید شامل حداقل یک حرف بزرگ باشد"
            } else if (!Pattern.compile("^(?=.*(?=.*[a-z])).{1,20}$").matcher(it.toString())
                    .matches()
            ) {
                passwordEditText.error = "رمز عبور باید شامل حداقل یک حرف کوچک باشد"
            } else if (!Pattern.compile("^(?=.*(?=.*[@#$%^&+=])).{1,20}$").matcher(it.toString())
                    .matches()
            ) {
                passwordEditText.error = "رمز عبور باید شامل حداقل یک سمبل خاص باشد"
            } else if (it.toString().length < 6) {
                passwordEditText.error = "رمز عبور باید شامل حداقل شش کاراکتر باشد"
            } else {
                passwordEditText.error = null
            }
        }
        passwordConfirmEditText.doAfterTextChanged {
            if (it.toString() != passwordEditText.text.toString()) {
                passwordConfirmEditText.error = "رمز عبور و تأیید رمز عبور با هم مغایرت دارد"
            } else {
                passwordConfirmEditText.error = null
            }
        }


        redirectBtn.setOnClickListener {
            emailEditText.setText("")
            passwordEditText.setText("")
            passwordConfirmEditText.setText("")
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
        }

        submitBtn.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val current = LocalDateTime.now().toLocalDate().toString()

            val prefs = PreferenceManager.getDefaultSharedPreferences(this)
            val json = prefs.getString("users", "[]").toString()
            val type = object : TypeToken<MutableList<User>>() {}.type
            val users = Gson().fromJson<MutableList<User>>(json, type)
            if (emailEditText.text.toString().isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(
                    emailEditText.text.toString()
                )
                    .matches() &&
                Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{6,20}$")
                    .matcher(passwordEditText.text.toString()).matches() &&
                passwordConfirmEditText.text.toString() == passwordEditText.text.toString()
            ) {
                users.add(User(email, password, current))
                prefs.edit().putString("users", Gson().toJson(users)).apply()
                prefs.edit().putBoolean("isLogin", true).apply()
                val intent = Intent(this, MainActivity::class.java)
                this.startActivity(intent)
            }

        }
    }
}