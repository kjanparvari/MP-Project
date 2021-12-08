package com.example.mp_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.mp_project.model.Advertisement
import com.example.mp_project.model.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myBooks = Datasource().loadBooks()
        val myAnnouncements = Datasource().loadAnnouncements()
        var showingAnnouncements: List<Advertisement>
        val myUsers = Datasource().loadUsers()
        val recyclerView = findViewById<RecyclerView>(R.id.announcements_recycler_view)

        val adInfo = intent.getSerializableExtra("ad") as? Advertisement
        if (adInfo == null) {
            showingAnnouncements = myAnnouncements
        } else{
            showingAnnouncements = filterAds(myAnnouncements, adInfo.book.title)
        }

        recyclerView.adapter = ItemAdapter(this, showingAnnouncements)
        recyclerView.setHasFixedSize(true)
    }
}

fun filterAds (
    src: List<Advertisement>,
    name: String,
//    minPrice: Int,
//    maxPrice: Int,
//    city: String,
): List<Advertisement> {
    return src.filter { it.book.title.contains(name, ignoreCase = true) }
}