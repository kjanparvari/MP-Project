package com.example.mp_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mp_project.model.Announcement

class ItemAdapter(private val context: Context, private val dataset: List<Announcement>) :

    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.announcement_item_title)
        val priceTextView: TextView = view.findViewById(R.id.announcement_item_price)
        val timeTextView: TextView = view.findViewById(R.id.announcement_item_time)
        val imageView: ImageView = view.findViewById(R.id.announcement_item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.announcement_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item: Announcement = dataset[position]
        holder.titleTextView.text = item.book.title
        holder.priceTextView.text = "قیمت : ${item.price}"
        holder.timeTextView.text = "تاریخ : ${item.time}"
        holder.imageView.setImageResource(item.book.imageId)
//        val intent = Intent(context, DetailActivity::class.java)
//        intent.putExtra("name", item.nameResourceId)
//        intent.putExtra("img", item.imageResourceId)
//        intent.putExtra("description", item.descriptionResourceId)
//        holder.imageView.setOnClickListener{context.startActivity(intent)}
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
