package com.dicoding.picodiploma.nusvarna.api

import com.dicoding.picodiploma.nusvarna.response.DetailResponse
import com.dicoding.picodiploma.nusvarna.response.LogResponse
import com.dicoding.picodiploma.nusvarna.response.ProfileResponse
import com.dicoding.picodiploma.nusvarna.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("login")
    fun getLogin(
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody
    ): Call<LogResponse>

    @Multipart
    @POST("register")
    fun getRegistry(
        @Part("email") email: RequestBody,
        @Part("password") password: RequestBody,
        @Part("username") username: RequestBody,
        @Part("nama") nama: RequestBody
    ): Call<RegisterResponse>

    @GET("profile")
    fun getProfile(
    ): Call<ProfileResponse>

    @Multipart
    @GET("profile/foto_profile")
    fun getProfilePhotoUpload(
        @Part file:MultipartBody.Part
    ): Call<RegisterResponse>

    @Multipart
    @POST("predict")
    fun getPredict(
        @Part image: MultipartBody.Part
    ): Call<DetailResponse>


}