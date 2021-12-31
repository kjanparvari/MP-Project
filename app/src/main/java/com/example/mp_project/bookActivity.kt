package com.example.mp_project

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mp_project.model.Advertisement
import com.example.mp_project.model.Book

class BookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)


        val bookInfo = intent.getSerializableExtra("book") as Book
        Log.d("hey", bookInfo.title)

        val textView: TextView = findViewById(R.id.related_text_4)
        val description: TextView = findViewById(R.id.description)
        val imageView: ImageView = findViewById(R.id.related_image_4)
        val image_book: ImageView = findViewById(R.id.book_picture)

        imageView.setImageResource(R.drawable.the_faut_in_our_stars)
        textView.text = "اشتباه در ستاره های بخت ما"
        image_book.setImageResource(bookInfo.imageId)
        val textwriter: TextView = findViewById(R.id.writer)
        textwriter.text = bookInfo.author
        val textpublisher: TextView = findViewById(R.id.publisher)
        textpublisher.text = "${"ناشر"}ناشر : "

        val textcat: TextView = findViewById(R.id.category)
        textcat.text = "دسته بندی : ${bookInfo.category}"
        val texttit: TextView = findViewById(R.id.title)
        texttit.text = bookInfo.title


        description.text = bookInfo.summary


    }
}