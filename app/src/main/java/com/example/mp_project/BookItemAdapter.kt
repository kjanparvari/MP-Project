package com.example.mp_project

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mp_project.model.Advertisement
import com.example.mp_project.model.Book

class BookItemAdapter (private val context: Context, private val dataset: List<Book>) :
    RecyclerView.Adapter<BookItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val container: LinearLayout = view.findViewById(R.id.book_item_container)
        val titleTextView: TextView = view.findViewById(R.id.book_item_title)
        val categoryTextView: TextView = view.findViewById(R.id.book_item_category)
        val scoreTextView: TextView = view.findViewById(R.id.book_item_score)
        val imageView: ImageView = view.findViewById(R.id.book_item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Book = dataset[position]
        holder.titleTextView.text = item.title
        holder.categoryTextView.text = "دسته بندی : ${item.category}"
        holder.scoreTextView.text = "امتیاز : ${item.score}"
        holder.imageView.setImageResource(item.imageId)
        val intentAd = Intent(context, BookActivity::class.java)
        intentAd.putExtra("book", item)
        holder.container.setOnClickListener{
            context.startActivity(intentAd)
        }
    }


    override fun getItemCount(): Int {
        return dataset.size
    }
}
