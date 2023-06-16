package com.dicoding.picodiploma.nusvarna.ui.profile

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.nusvarna.ui.camera.CameraActivity
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.databinding.ActivityProfileSettingBinding
import com.dicoding.picodiploma.nusvarna.response.ProfileResponse
import com.dicoding.picodiploma.nusvarna.ui.auth.ForgetpassActivity
import com.dicoding.picodiploma.nusvarna.utils.ViewModelFactory
import com.dicoding.picodiploma.nusvarna.utils.rotateFile
import java.io.File

class ProfileSettingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileSettingBinding
    private lateinit var profileSettingViewModel: ProfileSettingViewModel
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileSettingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Mendapatkan objek ActionBar
        val actionBar = supportActionBar

        // Mengatur tampilan kustom untuk ActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setCustomView(R.layout.custom_actionbar)
        actionBar?.setDisplayShowTitleEnabled(false)
        val drawable = ContextCompat.getDrawable(this, R.color.main)

        actionBar?.setBackgroundDrawable(drawable)

        profileSettingViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[ProfileSettingViewModel::class.java]
        profileSettingViewModel.getLogin()

        profileSettingViewModel.profile.observe(this){
            setUserData(it)
        }

        binding.uploadPhoto.setOnClickListener { startCameraX() }

        val imgFile = intent.getParcelableExtra<Uri>("key_img")
        binding.registerIconProfilesetting.setImageURI(imgFile)


    }

    override fun onBackPressed() {
        startActivity(Intent(this@ProfileSettingActivity, ProfileActivity::class.java))
    }

    private fun startCameraX() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as? File

            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            myFile?.let { file ->
                rotateFile(file, isBackCamera)
                getFile = file
                binding.registerIconProfilesetting.setImageBitmap(BitmapFactory.decodeFile(file.path))
            }
        }
    }

    private fun setUserData(profile: ProfileResponse) {
        binding.registerUsernameHint.text = profile.username
        binding.registerEmailHint.text = profile.email
        Glide.with(binding.registerIconProfilesetting).load(profile.profile_photo_url).into(binding.registerIconProfilesetting)
    }

    companion object{
        const val CAMERA_X_RESULT = 200
    }
}