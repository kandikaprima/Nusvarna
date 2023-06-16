package com.dicoding.picodiploma.nusvarna.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class DataDetailDeteksi(
    val predictedLabel: String,
    val picture: Array<String>,
    val name: String,
    val loc: String,
    val tempatWisataString: Array<String>,
    val tempatWisataNamaString: Array<String>,
    val makananKhas: Array<String>,
    val makananKhasNama: Array<String>,
    val bangunanBersejarah: Array<String>,
    val bangunanBersejaraNama: Array<String>
): Parcelable