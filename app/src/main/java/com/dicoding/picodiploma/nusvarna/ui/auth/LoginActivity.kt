package com.dicoding.picodiploma.nusvarna.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.nusvarna.R
import com.dicoding.picodiploma.nusvarna.databinding.ActivityLoginBinding
import com.dicoding.picodiploma.nusvarna.ui.main.MainActivity
import com.dicoding.picodiploma.nusvarna.utils.PreferenceHelper
import com.dicoding.picodiploma.nusvarna.utils.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        loginViewModel = ViewModelProvider(this, ViewModelFactory.getInstance())[LoginViewModel::class.java]
        loginViewModel.responseMessage.observe(this){ message ->
            Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
            if (message == "Berhasil Login !"){
                val intentMain = Intent(this, MainActivity::class.java)
                startActivity(intentMain)
            }
        }

        loginViewModel.isLoading.observe(this){
            showLoading(isLoading = it)
        }

        binding.goToRegister1.setOnClickListener{
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        binding.loginButton.setOnClickListener {
            val email = binding.edLoginEmail.text.toString()
            val password = binding.edForgetpassPassword.text.toString()
            loginViewModel.getLogin(email, password)
        }

        binding.forgotYourPass.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgetpassActivity::class.java))
        }


    }

    private fun showLoading(isLoading: Boolean) {
        binding.loginProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE

    }
}