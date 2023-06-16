package com.dicoding.picodiploma.nusvarna.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.nusvarna.repository.Repository
import com.dicoding.picodiploma.nusvarna.response.LogResponse
import com.dicoding.picodiploma.nusvarna.response.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(val repository: Repository): ViewModel() {

    val profile = MutableLiveData<ProfileResponse>()
    val isLoading = MutableLiveData<Boolean>()

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> get() = _responseMessage

    fun getLogin(){
        isLoading.value = true
        val client = repository.postProfile()
        client.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful){
                    isLoading.value = false
                    profile.value = response.body()

                }else{
                    isLoading.value = false
                    _responseMessage.postValue("Gagal mendapatkan profil")
                    Log.d("Failure", response.message().toString())
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                isLoading.value = false
                _responseMessage.postValue("Terjadi Kesalahan")
                Log.d("Error", t.message.toString())
            }

        })
    }

}