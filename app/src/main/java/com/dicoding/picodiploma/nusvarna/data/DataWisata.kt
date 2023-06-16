package com.dicoding.picodiploma.nusvarna.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataWisata(
    val picture: String,
    val name: String,
) : Parcelable