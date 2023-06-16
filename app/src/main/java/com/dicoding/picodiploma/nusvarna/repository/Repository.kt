package com.dicoding.picodiploma.nusvarna.repository

import com.dicoding.picodiploma.nusvarna.api.ApiConfig
import com.dicoding.picodiploma.nusvarna.api.ApiService
import com.dicoding.picodiploma.nusvarna.response.DetailResponse
import com.dicoding.picodiploma.nusvarna.response.LogResponse
import com.dicoding.picodiploma.nusvarna.response.ProfileResponse
import com.dicoding.picodiploma.nusvarna.response.RegisterResponse
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import java.io.File

class Repository(private val apiService: ApiService) {


    fun postLogin(email: String, password: String): Call<LogResponse> {
        val emailReq = email.toRequestBody()
        val passReq = password.toRequestBody()
        return apiService.getLogin(emailReq, passReq)
    }

    fun postRegistry(email: String, password: String, username: String, nama: String): Call<RegisterResponse> {
        val emailReq = email.toRequestBody()
        val passReq = password.toRequestBody()
        val userReq = username.toRequestBody()
        val namaReq = nama.toRequestBody()
        return apiService.getRegistry(emailReq, passReq, userReq, namaReq)
    }

    fun postProfile():  Call<ProfileResponse>{
        return apiService.getProfile()
    }

    fun postPredict(image: MultipartBody.Part): Call<DetailResponse>{
        return apiService.getPredict(image)
    }



    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository {
            return instance ?: synchronized(Repository::class.java){
                if (instance == null){
                    instance = Repository(ApiConfig.getApiService())
                }
                return instance as Repository
            }
        }
    }

}