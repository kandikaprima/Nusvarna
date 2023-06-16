package com.dicoding.picodiploma.nusvarna.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.data.BajuAdat
import com.dicoding.picodiploma.nusvarna.data.Evarna

class ListEvarnaAdapter(private val listEvarna: ArrayList<Evarna>) : RecyclerView.Adapter<ListEvarnaAdapter.ListViewHolder>() {


    override fun getItemCount(): Int = listEvarna.size


    //fungsi untuk inisiasi variabel untuk memuat data dari layout_list
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val devPict: ImageView = itemView.findViewById(R.id.evarna_image)
        val devSewa: TextView = itemView.findViewById(R.id.evarna_disewakan)
        val devName: TextView = itemView.findViewById(R.id.evarna_card_title)
        val devPrice: TextView = itemView.findViewById(R.id.evarna_card_price)
        val devLoc: TextView = itemView.findViewById(R.id.evarna_card_loc)
        val devRating: TextView = itemView.findViewById(R.id.evarna_card_rating)


        fun bind (developer: Evarna){
            devName.setOnClickListener {
                Toast.makeText(itemView.context, developer.name, Toast.LENGTH_SHORT).show()
            }
        }

    }

    //fungsi untuk memberi tempat untuk data dari layout list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.evarna_card, parent, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val (picture, sewa, title, price,loc, rating) = listEvarna[position]
        //berurutan sesuai dengan Arraylist<Game>. artinya inisialisasi variabel diatas tidak sembarangan. inisialisasi wajib mengikuti urutan dari arraylist
        holder.devPict.setImageResource(picture)
        holder.devSewa.text = sewa
        holder.devName.text = title
        holder.devPrice.text = price
        holder.devLoc.text = loc
        holder.devRating.text = rating

        holder.bind(listEvarna[position])

    }



}