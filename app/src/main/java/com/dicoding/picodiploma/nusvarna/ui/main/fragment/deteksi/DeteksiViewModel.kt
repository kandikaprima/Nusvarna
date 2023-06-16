package com.dicoding.picodiploma.nusvarna.ui.main.fragment.deteksi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.nusvarna.repository.Repository
import com.dicoding.picodiploma.nusvarna.response.DetailResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeteksiViewModel(val repository: Repository): ViewModel() {

    val profile = MutableLiveData<DetailResponse>()
    val isLoading = MutableLiveData<Boolean>()

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> get() = _responseMessage

    fun getPredict(image: MultipartBody.Part){
        isLoading.value = true
        val client = repository.postPredict(image)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                if (response.isSuccessful){
                    isLoading.value = false
                    profile.value = response.body()

                }else{
                    isLoading.value = false
                    _responseMessage.postValue("Gagal Mengupload foto")
                    Log.d("Failure", response.message().toString())
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                isLoading.value = false
                _responseMessage.postValue("Terjadi Kesalahan")
                Log.d("Error", t.message.toString())
            }

        })
    }

}