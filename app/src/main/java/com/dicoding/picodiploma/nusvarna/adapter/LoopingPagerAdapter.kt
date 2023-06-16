package com.dicoding.picodiploma.nusvarna.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.data.EventCard
import com.google.android.material.internal.ViewUtils.getBackgroundColor

class LoopingPagerAdapter(
    itemList: ArrayList<EventCard>,
    isInfinite: Boolean
) : LoopingPagerAdapter<EventCard>(itemList, isInfinite) {

    //This method will be triggered if the item View has not been inflated before.
    override fun inflateView(
        viewType: Int,
        container: ViewGroup,
        listPosition: Int
    ): View {
        return LayoutInflater.from(container.context).inflate(R.layout.event_card, container, false)
    }

    //Bind your data with your item View here.
    //Below is just an example in the demo app.
    //You can assume convertView will not be null here.
    //You may also consider using a ViewHolder pattern.
    override fun bindView(
        convertView: View,
        listPosition: Int,
        viewType: Int
    ) {

        val name : TextView = convertView.findViewById(R.id.eventcard_name)
        val loc = convertView.findViewById<TextView>(R.id.eventcard_loc)

        name.text = "Parade adat jam gadang"
        loc.text = "Bukit tinggi"

        convertView.findViewById<View>(R.id.eventcard_image).background = convertView.context.resources.getDrawable(R.drawable.eventcardimage)

    }
}