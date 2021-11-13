package com.example.mp_project

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class bookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book)



        val textView: TextView = findViewById(R.id.related_text_4)
        val imageView: ImageView = findViewById(R.id.related_image_4)

        //imageView.setImageResource(imageResourceId)
        textView.text = "ali amad"
    }
}