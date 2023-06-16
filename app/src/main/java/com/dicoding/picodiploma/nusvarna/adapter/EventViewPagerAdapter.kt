package com.dicoding.picodiploma.nusvarna.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.nusvarna.R

class EventViewPagerAdapter(private var title: List<String>, private var details: List<String>, private var images: List<Int>) : RecyclerView.Adapter<EventViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView = itemView.findViewById(R.id.eventcard_name)
        val itemDetails: TextView = itemView.findViewById(R.id.eventcard_loc)
        val itemImage: ImageView = itemView.findViewById(R.id.eventcard_image)


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.event_card, parent, false))
    }

    override fun onBindViewHolder(holder: EventViewPagerAdapter.Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemDetails.text = details[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return title.size
    }
}