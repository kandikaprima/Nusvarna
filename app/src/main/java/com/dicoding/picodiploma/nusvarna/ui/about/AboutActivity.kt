package com.dicoding.picodiploma.nusvarna.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.data.Developer
import com.dicoding.picodiploma.nusvarna.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAboutBinding

    private lateinit var rvDevs: RecyclerView //inisialisasi variabel recyclerview games
    private val list = ArrayList<Developer>() //variabel untuk menampung data Game.kt dalam bentuk arraylist


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan objek ActionBar
        val actionBar = supportActionBar

        // Mengatur tampilan kustom untuk ActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setCustomView(R.layout.about_actionbar)
        actionBar?.setDisplayShowTitleEnabled(false)
        val drawable = ContextCompat.getDrawable(this, R.color.main)
        actionBar?.setBackgroundDrawable(drawable)

        rvDevs = binding.rvDev
        rvDevs.setHasFixedSize(true)

        list.addAll(getListDevs())
        showRecyclerList()
    }

    private fun getListDevs(): ArrayList<Developer> {

        val dataPict = resources.obtainTypedArray(R.array.dev_picture)
        val dataName = resources.getStringArray(R.array.dev_name)
        val dataLinkedin = resources.getStringArray(R.array.dev_linkedin)
        val dataGithub = resources.getStringArray(R.array.dev_github)
        val dataPath = resources.getStringArray(R.array.dev_path)
        val listGame = ArrayList<Developer>()
        for (i in dataName.indices) {
            val game = Developer(dataPict.getResourceId(i, -1), dataName[i], dataLinkedin[i],dataGithub[i],dataPath[i])
            listGame.add(game)
        }
        return listGame
    }

    private fun showRecyclerList() {
        rvDevs.layoutManager = LinearLayoutManager(this)
        val listGameAdapter = ListDevAdapter(list)
        rvDevs.adapter = listGameAdapter
    }

}