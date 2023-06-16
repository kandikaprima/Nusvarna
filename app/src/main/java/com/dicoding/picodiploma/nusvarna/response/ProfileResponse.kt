package com.dicoding.picodiploma.nusvarna.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("poin")
	val poin: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("3")
	val profile_photo_url: String? = null
)
