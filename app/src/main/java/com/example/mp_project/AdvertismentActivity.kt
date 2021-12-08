package com.example.mp_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.mp_project.model.Advertisement

class AdvertismentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertisment)
        val title: TextView = findViewById(R.id.adTitle)
        val price: TextView = findViewById(R.id.adPrice)
        val time: TextView = findViewById(R.id.adTime)
        val picture: ImageView = findViewById(R.id.adPicture)
        val city: TextView = findViewById(R.id.adCity)
        val description: TextView= findViewById(R.id.adDescription)
        val phoneNumber: TextView = findViewById(R.id.adPhoneNumber)
        val relatedAdsBtn: Button = findViewById(R.id.adRelatedAds)

        val adInfo = intent.getSerializableExtra("ad") as Advertisement
        picture.setImageResource(adInfo.imageId)
        title.text = adInfo.book.title
        price.text = "${adInfo.price} تومان"
        time.text = "زمان : ${adInfo.time}"
        city.text = "شهر : ${adInfo.city}"
        description.text = adInfo.description
        phoneNumber.text = adInfo.phoneNumber

        val relatedAdsIntent = Intent(this, MainActivity::class.java)
        relatedAdsIntent.putExtra("ad", adInfo)
        relatedAdsBtn.setOnClickListener{
            this.startActivity(relatedAdsIntent)
        }

    }



}