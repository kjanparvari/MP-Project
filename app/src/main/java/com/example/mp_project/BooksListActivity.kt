package com.example.mp_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.mp_project.model.Book
import com.example.mp_project.model.Datasource

class BooksListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books_list)

        val myBooks = Datasource().loadBooks()
        val showingBooks: List<Book>
        val recyclerView = findViewById<RecyclerView>(R.id.books_list_recycler_view)
        recyclerView.adapter = BookItemAdapter(this, myBooks)
        recyclerView.setHasFixedSize(true)
    }
}