package com.example.mp_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.mp_project.model.Advertisement
import com.example.mp_project.model.Datasource
import android.preference.PreferenceManager

import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

import android.content.SharedPreferences
import android.util.Log
import androidx.navigation.ui.AppBarConfiguration
import com.example.mp_project.model.Book
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupBottomNavMenu(navController)
        setupActionBar(navController)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
//        prefs.edit().clear().apply()
//        val isLogin = prefs.getBoolean("isLogin", false)
        val isLogin = true
        Log.d("main", prefs.all.toString())
        val isLogin2 = false
        if (isLogin) {   // condition true means user is already login

        } else {
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
        }
        val adInfo = intent.getSerializableExtra("ad") as? Advertisement
        val adFragment = AdvertisementsFragment.newInstance(adInfo)

        val bookInfo = intent.getSerializableExtra("book") as? Book
        val bookFragment = BooksFragment.newInstance(bookInfo)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        bottom_nav?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }
    private fun setupActionBar(navController: NavController) {
        NavigationUI.setupActionBarWithNavController(this, navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

}
