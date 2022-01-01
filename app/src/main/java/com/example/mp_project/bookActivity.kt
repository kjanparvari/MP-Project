package com.example.mp_project

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mp_project.model.Advertisement
import com.example.mp_project.model.Book
import com.example.mp_project.serverModel.books.ApiBooksListInterface
import com.example.mp_project.serverModel.books.BooksListItem
import com.example.mp_project.serverModel.books.BooksListItemAdapter
import com.example.mp_project.serverModel.requestJson.RequestJsonId
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book.view.*
import kotlinx.android.synthetic.main.books_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)


        val bookId = intent.getSerializableExtra("book") as String
        Log.d("hey", bookId)

        val textView: TextView = findViewById(R.id.related_text_4)
        val description: TextView = findViewById(R.id.description)
        val imageView: ImageView = findViewById(R.id.related_image_4)
        val image_book: ImageView = findViewById(R.id.book_picture)
        val textwriter: TextView = findViewById(R.id.writer)
        val textcat: TextView = findViewById(R.id.category)
        val texttit: TextView = findViewById(R.id.title)
        val textpublisher: TextView = findViewById(R.id.publisher)


        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiBooksListInterface::class.java)

        val retrofitData = retrofitBuilder.getBookById(RequestJsonId(bookId))

        retrofitData.enqueue(object : Callback<BooksListItem?> {
            override fun onResponse(
                call: Call<BooksListItem?>,
                response: Response<BooksListItem?>
            ) {
                val responseBody = response.body()!!
//                textView.text = responseBody.title
                description.text = responseBody.summary
                Picasso.get()
                    .load(com.example.mp_project.serverModel.advertisements.BASE_URL + responseBody.imageUrl)
                    .into(image_book)
                textcat.text = "دسته بندی : ${responseBody.category}"
                textwriter.text = responseBody.author
                texttit.text = responseBody.title
                textpublisher.text = "ناشر : ${responseBody.publisher}"

//                setContentView(R.layout.activity_book)



            }

            override fun onFailure(call: Call<BooksListItem?>, t: Throwable) {
                Log.d("Program", "Failure: " + t.message)
            }
        })
//
//        imageView.setImageResource(R.drawable.the_faut_in_our_stars)
//        textView.text = "اشتباه در ستاره های بخت ما"
//        image_book.setImageResource(bookInfo.imageId)
//        val textwriter: TextView = findViewById(R.id.writer)
//        textwriter.text = bookInfo.author
//        val textpublisher: TextView = findViewById(R.id.publisher)
//        textpublisher.text = "${"ناشر"}ناشر : "
//
//        val textcat: TextView = findViewById(R.id.category)
//        textcat.text = "دسته بندی : ${bookInfo.category}"
//        val texttit: TextView = findViewById(R.id.title)
//        texttit.text = bookInfo.title
//
//
//        description.text = bookInfo.summary

//        imageView.setImageResource(R.drawable.the_faut_in_our_stars)
//        textView.text = bookInfo
//        image_book.setImageResource(bookInfo.imageId)
//        val textwriter: TextView = findViewById(R.id.writer)
//        textwriter.text = bookInfo.author
//        val textpublisher: TextView = findViewById(R.id.publisher)
//        textpublisher.text = "${"ناشر"}ناشر : "
//
//        val textcat: TextView = findViewById(R.id.category)
//        textcat.text = "دسته بندی : ${bookInfo.category}"
//        val texttit: TextView = findViewById(R.id.title)
//        texttit.text = bookInfo.title


//        description.text = bookInfo.summary


    }
}