package com.example.mp_project.serverModel.books

import com.example.mp_project.BookActivity
import com.example.mp_project.R

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
import com.example.mp_project.serverModel.advertisements.BASE_URL
import com.squareup.picasso.Picasso

class BooksListItemAdapter(
    private val context: Context,
    private val dataset: List<BooksListItem>
) :
    RecyclerView.Adapter<BooksListItemAdapter.ItemViewHolder>() {
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
        val item: BooksListItem = dataset[position]
        holder.titleTextView.text = item.title
        holder.categoryTextView.text = "دسته بندی : ${item.category}"
        holder.scoreTextView.text = "امتیاز : ${item.score}"
//        holder.imageView.setImageResource(item.imageId)
        Picasso.get().load(BASE_URL + item.imageUrl).into(holder.imageView)

        val intentAd = Intent(context, BookActivity::class.java)
        intentAd.putExtra("book", item._id)
        holder.container.setOnClickListener {
            context.startActivity(intentAd)
        }
    }


    override fun getItemCount(): Int {
        return dataset.size
    }
}
