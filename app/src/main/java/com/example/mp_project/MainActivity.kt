package com.example.mp_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.mp_project.model.Advertisement
import com.example.mp_project.model.Datasource
import android.preference.PreferenceManager

import android.content.SharedPreferences
import android.util.Log


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
//        prefs.edit().clear().apply()
        val isLogin = prefs.getBoolean("isLogin", false)
        Log.d("main", prefs.all.toString())
        val isLogin2 = false
        if (isLogin2) {   // condition true means user is already login

        } else {
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
        }
        val myBooks = Datasource().loadBooks()
        val myAnnouncements = Datasource().loadAnnouncements()
        val showingAnnouncements: List<Advertisement>
        val myUsers = Datasource().loadUsers()
        val recyclerView = findViewById<RecyclerView>(R.id.announcements_recycler_view)

        val adInfo = intent.getSerializableExtra("ad") as? Advertisement
        showingAnnouncements = if (adInfo == null) {
            myAnnouncements
        } else {
            filterAds(myAnnouncements, adInfo.book.title)
        }

        recyclerView.adapter = ItemAdapter(this, showingAnnouncements)
        recyclerView.setHasFixedSize(true)

    }
}

fun filterAds(
    src: List<Advertisement>,
    name: String,
//    minPrice: Int,
//    maxPrice: Int,
//    city: String,
): List<Advertisement> {
    return src.filter { it.book.title.contains(name, ignoreCase = true) }
}