package com.dicoding.picodiploma.nusvarna.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.nusvarna.databinding.ActivityForgetpassBinding

class ForgetpassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgetpassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetpassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

    }
}