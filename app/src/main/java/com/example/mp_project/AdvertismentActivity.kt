package com.example.mp_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

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

        picture.setImageResource(R.drawable.the_faut_in_our_stars)
        title.text = "اشتباه در ستاره های بخت ما"
        price.text = "60000 تومان"
        time.text = "زمان : دیروز"
        city.text = "شهر : تهران"
        description.text = "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد کتابهای زیادی در شصت و سه درصد گذشته حال و آینده شناخت فراوان جامعه و متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها "
        phoneNumber.text = "09130012682"

    }



}