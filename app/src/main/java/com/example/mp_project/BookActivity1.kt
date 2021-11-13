package com.example.mp_project

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book1)



        val textView: TextView = findViewById(R.id.related_text_4)
        val description: TextView = findViewById(R.id.description)
        val imageView: ImageView = findViewById(R.id.related_image_4)
        val image_book: ImageView = findViewById(R.id.book_picture)

        imageView.setImageResource(R.drawable.the_faut_in_our_stars)
        textView.text = "اشتباه در ستاره های بخت ما"
        image_book.setImageResource(R.drawable.harry_potter_and_the_philosopher_s_stone)
        val textwriter: TextView = findViewById(R.id.writer)
        textwriter.text = "نویسنده : J.K. Rowling"
        val textpublisher: TextView = findViewById(R.id.publisher)
        textpublisher.text = "ناشر : "

        val textcat: TextView = findViewById(R.id.category)
        textcat.text = "دسته بندی : رمان"
        val texttit: TextView = findViewById(R.id.title)
        texttit.text = "عنوان کتاب : هری پاتر و سنگ جادو"


        description.text = " The Fault In Our Stars is a fabulous book about a young teenage girl who has been diagnosed with lung cancer and attends a cancer support group. Hazel is 16 and is reluctant to go to the support group, but she soon realises that it was a good idea. Hazel meets a young boy named Augustus Waters."


    }
}