package com.dicoding.picodiploma.nusvarna.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Evarna(
    val picture: Int,
    val sewa: String,
    val name: String,
    val price: String,
    val loc: String,
    val rating: String,
) : Parcelable
