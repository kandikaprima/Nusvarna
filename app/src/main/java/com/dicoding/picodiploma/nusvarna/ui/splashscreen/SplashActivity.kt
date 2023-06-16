package com.dicoding.picodiploma.nusvarna.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.data.DataDetailDeteksi
import com.dicoding.picodiploma.nusvarna.data.DataDetailDeteksiData
import com.dicoding.picodiploma.nusvarna.data.DataDetailDeteksiData.dataDetail
import com.dicoding.picodiploma.nusvarna.ui.auth.LoginActivity
import com.dicoding.picodiploma.nusvarna.ui.auth.RegisterActivity
import com.dicoding.picodiploma.nusvarna.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        Handler().postDelayed( Runnable(){
            run(){
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }
        }, 3000)



        }




    }
