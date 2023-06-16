package com.dicoding.picodiploma.nusvarna.ui.about

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.data.Developer


class ListDevAdapter(private val listDev: ArrayList<Developer>) : RecyclerView.Adapter<ListDevAdapter.ListViewHolder>() {


    override fun getItemCount(): Int = listDev.size


    //fungsi untuk inisiasi variabel untuk memuat data dari layout_list
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val devPict: ImageView = itemView.findViewById(R.id.eventcard_image)
        val devName: TextView = itemView.findViewById(R.id.dev_name1)
        val devLinkedin: TextView = itemView.findViewById(R.id.eventcard_name)
        val devGithub: TextView = itemView.findViewById(R.id.dev_github_hyperlink)
        val devPath: TextView = itemView.findViewById(R.id.dev_path)

        fun bind (developer: Developer){
            devLinkedin.setOnClickListener {
                Toast.makeText(itemView.context, developer.linkedin, Toast.LENGTH_SHORT).show()
            }
        }

    }

    //fungsi untuk memberi tempat untuk data dari layout list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.aboutdev_layout, parent, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        val (picture, name, linkedin, github, path) = listDev[position]
        //berurutan sesuai dengan Arraylist<Game>. artinya inisialisasi variabel diatas tidak sembarangan. inisialisasi wajib mengikuti urutan dari arraylist
        holder.devPict.setImageResource(picture)
        holder.devName.text = name
        holder.devLinkedin.text = linkedin
        holder.devGithub.text = github
        holder.devPath.text = path

        holder.bind(listDev[position])

    }



}
