package com.dicoding.picodiploma.nusvarna.ui.detaildeteksi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.databinding.ActivityDetailDeteksiBinding
import com.dicoding.picodiploma.nusvarna.ui.main.MainActivity
import com.dicoding.picodiploma.nusvarna.ui.main.fragment.beranda.BerandaFragment
import com.dicoding.picodiploma.nusvarna.ui.main.fragment.deteksi.DeteksiFragment

class DetailDeteksiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDeteksiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDeteksiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar = supportActionBar

        // Mengatur tampilan kustom untuk ActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setCustomView(R.layout.about_actionbar)
        actionBar?.setDisplayShowTitleEnabled(false)
        val drawable = ContextCompat.getDrawable(this, R.color.main)

        actionBar?.setBackgroundDrawable(drawable)

        setFragment(DetailDeteksiFragment.newInstance())

        val backButton = findViewById<ImageView>(R.id.register_icon_goback)
        backButton.setOnClickListener {
            finish()
        }





    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout_detail, fragment, "detailDeteksiActivity")
            .commit()
    }



}