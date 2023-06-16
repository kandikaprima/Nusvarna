package com.dicoding.picodiploma.nusvarna.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.data.BajuAdat
import com.dicoding.picodiploma.nusvarna.data.DataWisata
import com.dicoding.picodiploma.nusvarna.data.Evarna

class WisataAdapter(private val listWisata: ArrayList<DataWisata>) : RecyclerView.Adapter<WisataAdapter.ListViewHolder>() {


    override fun getItemCount(): Int = listWisata.size


    //fungsi untuk inisiasi variabel untuk memuat data dari layout_list
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val devPict: ImageView = itemView.findViewById(R.id.wisata)
        val devSewa: TextView = itemView.findViewById(R.id.wisata_name)


        fun bind (developer: DataWisata){
        }

    }

    //fungsi untuk memberi tempat untuk data dari layout list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.wisata_layout, parent, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val (picture, sewa) = listWisata[position]
        //berurutan sesuai dengan Arraylist<Game>. artinya inisialisasi variabel diatas tidak sembarangan. inisialisasi wajib mengikuti urutan dari arraylist
        Glide.with(holder.devPict).load(picture).into(holder.devPict)
        holder.devSewa.text = sewa

        holder.bind(listWisata[position])

    }



}