package com.dicoding.picodiploma.nusvarna.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.databinding.ActivityMainBinding
import com.dicoding.picodiploma.nusvarna.response.ProfileResponse
import com.dicoding.picodiploma.nusvarna.ui.auth.LoginActivity
import com.dicoding.picodiploma.nusvarna.ui.main.fragment.beranda.BerandaFragment
import com.dicoding.picodiploma.nusvarna.ui.main.fragment.pameran.PameranFragment
import com.dicoding.picodiploma.nusvarna.ui.main.fragment.deteksi.DeteksiFragment
import com.dicoding.picodiploma.nusvarna.ui.main.fragment.evarna.EvarnaFragment
import com.dicoding.picodiploma.nusvarna.ui.profile.ProfileActivity
import com.dicoding.picodiploma.nusvarna.utils.PreferenceHelper
import com.dicoding.picodiploma.nusvarna.utils.ViewModelFactory
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val preferenceHelper = PreferenceHelper()

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan objek ActionBar
        val actionBar = supportActionBar

        // Mengatur tampilan kustom untuk ActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setCustomView(R.layout.actionbar_main)
        actionBar?.setDisplayShowTitleEnabled(false)
        val drawable = ContextCompat.getDrawable(this, R.color.main)

        actionBar?.setBackgroundDrawable(drawable)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        val token = preferenceHelper.getToken()
        if (token == null){
            Toast.makeText(this@MainActivity, "Silahkan login terlebih dahulu", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }

        mainViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[MainViewModel::class.java]
        mainViewModel.getLogin()

        mainViewModel.profile.observe(this){
            setUserData(it)
        }

        val bottomNavigation = binding.bottomBar

        setFragment(BerandaFragment.newInstance())
        bottomNavigation.onItemSelected = {
            when(it){
                0 -> setFragment(BerandaFragment.newInstance())
                1 -> setFragment(PameranFragment.newInstance())
                2 -> setFragment(DeteksiFragment.newInstance())
                3 -> setFragment(EvarnaFragment.newInstance())
            }
        }


        //button imageview
        findViewById<ImageView>(R.id.profile_actionbar).setOnClickListener {
            startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
        }

    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, fragment, "mainActivity")
            .commit()
    }

    private fun setUserData(profile: ProfileResponse) {
        val profileAction = findViewById<CircleImageView>(R.id.profile_actionbar)

        Glide.with(profileAction).load(profile.profile_photo_url).into(profileAction)
    }

    companion object{

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10



    }

}