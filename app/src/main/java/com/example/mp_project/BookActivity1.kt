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
        texttit.text = "هری پاتر و سنگ جادو"


        description.text = "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است چاپگرها و متون بلکه روزنامه و مجله در ستون و سطرآنچنان که لازم است و برای شرایط فعلی تکنولوژی مورد نیاز و کاربردهای متنوع با هدف بهبود ابزارهای کاربردی می باشد کتابهای زیادی در شصت و سه درصد گذشته حال و آینده شناخت فراوان جامعه و متخصصان را می طلبد تا با نرم افزارها شناخت بیشتری را برای طراحان رایانه ای علی الخصوص طراحان خلاقی و فرهنگ پیشرو در زبان فارسی ایجاد کرد در این صورت می توان امید داشت که تمام و دشواری موجود در ارائه راهکارها "


    }
}