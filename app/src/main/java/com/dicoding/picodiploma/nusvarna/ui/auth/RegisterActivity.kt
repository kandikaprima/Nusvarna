package com.dicoding.picodiploma.nusvarna.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.databinding.ActivityRegisterBinding
import com.dicoding.picodiploma.nusvarna.ui.main.MainActivity
import com.dicoding.picodiploma.nusvarna.utils.ViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        registerViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[RegisterViewModel::class.java]
        registerViewModel.responseMessage.observe(this){ message ->
            Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_SHORT ).show()
            if (message == "Data berhasil disimpan !"){
                val intentMain = Intent(this, LoginActivity::class.java)
                startActivity(intentMain)
            }
        }

        registerViewModel.isLoading.observe(this){
            showLoading(isLoading = it)
        }

        binding.registerButton.setOnClickListener{
            val nama = binding.edRegisterFullName.text.toString()
            val email = binding.edRegisterEmail.text.toString()
            val password = binding.edRegisterPass.text.toString()
            val username = binding.edRegisterUsername.text.toString()

            registerViewModel.getRegistry(email, password, username, nama)

//            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.registerProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }

}