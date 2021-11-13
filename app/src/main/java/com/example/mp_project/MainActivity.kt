package com.example.mp_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.mp_project.model.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myBooks = Datasource().loadBooks()
        val myAnnouncements = Datasource().loadAnnouncements()
        val myUsers = Datasource().loadUsers()
        val recyclerView = findViewById<RecyclerView>(R.id.announcements_recycler_view)
        recyclerView.adapter = ItemAdapter(this, myAnnouncements)
        recyclerView.setHasFixedSize(true)
        val intent = Intent(this, BookActivity1::class.java)
        this.startActivity(intent)
        val intentAd = Intent(this, AdvertismentActivity::class.java)
//        this.startActivity(intentAd)
    }
}