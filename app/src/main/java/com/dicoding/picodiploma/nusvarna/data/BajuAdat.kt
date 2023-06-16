package com.dicoding.picodiploma.nusvarna.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BajuAdat(
    val picture: Int,
    val name: String
) : Parcelable
