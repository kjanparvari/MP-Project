package com.example.mp_project.serverModel.advertisements

import android.content.Context
import android.content.Intent
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mp_project.AdvertismentActivity
import com.example.mp_project.R
import com.example.mp_project.model.Advertisement
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

const val BASE_URL = "https://agile-reaches-72185.herokuapp.com/api/"

class AdvertisementListItemAdapter(
    private val context: Context,
    private val dataset: List<AdvertisementsListItem>
) :

    RecyclerView.Adapter<AdvertisementListItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val container: LinearLayout = view.findViewById(R.id.advertisement_item_container)
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
        val item: AdvertisementsListItem = dataset[position]
        holder.titleTextView.text = item.title
        holder.priceTextView.text = "قیمت : ${item.price}"
//        holder.timeTextView.text = "تاریخ : ${LocalDate.parse(item.time,DateTimeFormatter.ISO_INSTANT).toString()}"
        holder.timeTextView.text =
            if (item.time == null || item.time.isEmpty()) "salam" else "تاریخ : ${item.time}"
        Picasso.get().load(BASE_URL + item.imageUrl).into(holder.imageView)

//        holder.imageView.setImageResource(item.imageId)
//        val intentAd = Intent(context, AdvertismentActivity::class.java)
//        intentAd.putExtra("ad", item)
//        holder.container.setOnClickListener {
//            context.startActivity(intentAd)
//        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
