package com.dicoding.picodiploma.nusvarna.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.data.EventCard


class EventAdapter(private val listEvent: ArrayList<EventCard>) : RecyclerView.Adapter<EventAdapter.ListViewHolder>() {


    override fun getItemCount(): Int = listEvent.size


    //fungsi untuk inisiasi variabel untuk memuat data dari layout_list
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val devPict: ImageView = itemView.findViewById(R.id.eventmingguini_image)
        val devName: TextView = itemView.findViewById(R.id.eventmingguini_name)
        val devLoc: TextView = itemView.findViewById(R.id.eventmingguini_loc)

        fun bind (developer: EventCard){
            devName.setOnClickListener {
                Toast.makeText(itemView.context, developer.name, Toast.LENGTH_SHORT).show()
            }
        }

    }

    //fungsi untuk memberi tempat untuk data dari layout list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_eventmingguini, parent, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val (picture, name, loc) = listEvent[position]
        //berurutan sesuai dengan Arraylist<Game>. artinya inisialisasi variabel diatas tidak sembarangan. inisialisasi wajib mengikuti urutan dari arraylist
        holder.devPict.setImageResource(picture)
        holder.devName.text = name
        holder.devLoc.text = loc

        holder.bind(listEvent[position])

    }



}
