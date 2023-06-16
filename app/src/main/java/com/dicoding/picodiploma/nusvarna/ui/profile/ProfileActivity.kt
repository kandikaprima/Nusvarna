package com.dicoding.picodiploma.nusvarna.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.databinding.ActivityProfileBinding
import com.dicoding.picodiploma.nusvarna.response.ProfileResponse
import com.dicoding.picodiploma.nusvarna.ui.about.AboutActivity
import com.dicoding.picodiploma.nusvarna.ui.auth.LoginActivity
import com.dicoding.picodiploma.nusvarna.ui.auth.RegisterActivity
import com.dicoding.picodiploma.nusvarna.ui.main.MainActivity
import com.dicoding.picodiploma.nusvarna.utils.PreferenceHelper
import com.dicoding.picodiploma.nusvarna.utils.ViewModelFactory

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding
    private val preferenceHelper = PreferenceHelper()

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val actionBar = supportActionBar

        // Mengatur tampilan kustom untuk ActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setCustomView(R.layout.about_actionbar)
        actionBar?.setDisplayShowTitleEnabled(false)
        val drawable = ContextCompat.getDrawable(this, R.color.main)
        actionBar?.setBackgroundDrawable(drawable)

        profileViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[ProfileViewModel::class.java]
        profileViewModel.getLogin()

        profileViewModel.profile.observe(this){
            setUserData(it)
        }



        binding.profileSettingContainer.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, ProfileSettingActivity::class.java))
        }

        binding.profileAboutContainer.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, AboutActivity::class.java))
        }

        binding.profileLogoutContainer.setOnClickListener {
            preferenceHelper.clearToken()
            Toast.makeText(this@ProfileActivity, "Berhasil Logout !", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
        }

    }

    override fun onBackPressed() {
        startActivity(Intent(this@ProfileActivity, MainActivity::class.java))
    }

    private fun setUserData(profile: ProfileResponse) {
        binding.profileUserName.text = profile.name
        binding.profileUserEmail.text = profile.email
        Glide.with(binding.profileUser).load(profile.profile_photo_url).into(binding.profileUser)
    }


}