package com.dicoding.picodiploma.nusvarna.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Developer(
    val picture: Int,
    val name: String,
    val linkedin: String,
    val github: String,
    val path: String
) : Parcelable
