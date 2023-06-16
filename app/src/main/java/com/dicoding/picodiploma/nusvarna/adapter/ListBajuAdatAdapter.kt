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


class ListBajuAdatAdapter(private val listBajuAdat: ArrayList<BajuAdat>) : RecyclerView.Adapter<ListBajuAdatAdapter.ListViewHolder>() {


    override fun getItemCount(): Int = listBajuAdat.size


    //fungsi untuk inisiasi variabel untuk memuat data dari layout_list
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val devPict: ImageView = itemView.findViewById(R.id.bajuadat)
        val devName: TextView = itemView.findViewById(R.id.bajuadat_name)

        fun bind (developer: BajuAdat){
            devName.setOnClickListener {
                Toast.makeText(itemView.context, developer.name, Toast.LENGTH_SHORT).show()
            }
        }

    }

    //fungsi untuk memberi tempat untuk data dari layout list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.bajuadat_layout, parent, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val (picture, name) = listBajuAdat[position]
        //berurutan sesuai dengan Arraylist<Game>. artinya inisialisasi variabel diatas tidak sembarangan. inisialisasi wajib mengikuti urutan dari arraylist
        holder.devPict.setImageResource(picture)
        holder.devName.text = name

        holder.bind(listBajuAdat[position])

    }



}
