package com.dicoding.picodiploma.nusvarna.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.nusvarna.repository.Repository
import com.dicoding.picodiploma.nusvarna.response.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(val authRepository: Repository): ViewModel() {

    val user = MutableLiveData<RegisterResponse>()
    val isLoading = MutableLiveData<Boolean>()

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> get() = _responseMessage


    fun getRegistry(email : String, password: String, username: String, nama: String) {
        isLoading.value = true
        val client = authRepository.postRegistry(email, password, username, nama)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    isLoading.value = false
                    _responseMessage.postValue("Data berhasil disimpan !")
//                    user.value = response.body()
                }else{
                    isLoading.value = false
                    _responseMessage.postValue("Gagal menyimpan data")
                    Log.d("Failure", response.message().toString())
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _responseMessage.postValue("Terjadi kesalahan")
                Log.d("Failure", t.message.toString())
            }

        })
    }

}