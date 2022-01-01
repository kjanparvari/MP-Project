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
import com.example.mp_project.serverModel.advertisements.AdvertisementsListItem
import com.example.mp_project.serverModel.advertisements.ApiAdvertisementsListInterface
import com.example.mp_project.serverModel.books.ApiBooksListInterface
import com.example.mp_project.serverModel.books.BooksListItem
import com.example.mp_project.serverModel.requestJson.RequestJsonId
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AdvertismentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advertisment)
        val title: TextView = findViewById(R.id.adTitle)
        val price: TextView = findViewById(R.id.adPrice)
        val time: TextView = findViewById(R.id.adTime)
        val picture: ImageView = findViewById(R.id.adPicture)
        val city: TextView = findViewById(R.id.adCity)
        val description: TextView = findViewById(R.id.adDescription)
        val phoneNumber: TextView = findViewById(R.id.adPhoneNumber)
        val relatedAdsBtn: Button = findViewById(R.id.adRelatedAds)

        val adId = intent.getSerializableExtra("ad") as String


        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiAdvertisementsListInterface::class.java)
        val retrofitData = retrofitBuilder.getAdvertisementById(RequestJsonId(adId))
        retrofitData.enqueue(object : Callback<AdvertisementsListItem?> {
            override fun onResponse(
                call: Call<AdvertisementsListItem?>,
                response: Response<AdvertisementsListItem?>
            ) {
                val responseBody = response.body()!!

                Picasso.get()
                    .load(com.example.mp_project.serverModel.advertisements.BASE_URL + responseBody.imageUrl)
                    .into(picture)
                title.text = responseBody.title
                price.text = "${responseBody.price} تومان"
                time.text = "زمان : ${responseBody.time}"
                city.text = "شهر : ${responseBody.city}"
                description.text = responseBody.description
                phoneNumber.text = responseBody.phoneNumber


            }

            override fun onFailure(call: Call<AdvertisementsListItem?>, t: Throwable) {
                Log.d("Program", "Failure: " + t.message)
            }
        })


//        picture.setImageResource(adInfo.imageId)
//        title.text = adInfo.book.title
//        price.text = "${adInfo.price} تومان"
//        time.text = "زمان : ${adInfo.time}"
//        city.text = "شهر : ${adInfo.city}"
//        description.text = adInfo.description
//        phoneNumber.text = adInfo.phoneNumber

//        val relatedAdsIntent = Intent(this, MainActivity::class.java)
//        relatedAdsIntent.putExtra("ad", adInfo)
//        relatedAdsBtn.setOnClickListener{
//            this.startActivity(relatedAdsIntent)
//        }

    }


}