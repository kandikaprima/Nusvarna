package com.dicoding.picodiploma.nusvarna.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.nusvarna.repository.Repository
import com.dicoding.picodiploma.nusvarna.response.LogResponse
import com.dicoding.picodiploma.nusvarna.utils.PreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(val repository: Repository): ViewModel() {

    val login = MutableLiveData<LogResponse>()
    val isLoading = MutableLiveData<Boolean>()

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> get() = _responseMessage

    val preferenceHelper = PreferenceHelper()

    fun getLogin(email: String, password: String){
        isLoading.value = true
        val client = repository.postLogin(email, password)
        client.enqueue(object : Callback<LogResponse> {
            override fun onResponse(call: Call<LogResponse>, response: Response<LogResponse>) {
                if (response.isSuccessful){
                    isLoading.value = false
                    _responseMessage.postValue("Berhasil Login !")
                    login.value = response.body()
                    //menyimpan token ke dalam preference
                    preferenceHelper.saveToken(login.value!!.accessToken )

                }else{
                    isLoading.value = false
                    _responseMessage.postValue("Gagal Login")
                    Log.d("Failure", response.message().toString())
                }
            }

            override fun onFailure(call: Call<LogResponse>, t: Throwable) {
                isLoading.value = false
                _responseMessage.postValue("Terjadi Kesalahan")
                Log.d("Error", t.message.toString())
            }

        })
    }


}

