package com.dicoding.picodiploma.nusvarna.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventCard(
    val picture: Int,
    val name: String,
    val loc: String
) : Parcelable
