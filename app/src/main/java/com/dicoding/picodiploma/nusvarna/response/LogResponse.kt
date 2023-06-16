package com.dicoding.picodiploma.nusvarna.response

import com.google.gson.annotations.SerializedName

data class LogResponse(

	@field:SerializedName("access_token")
	val accessToken: String
)
