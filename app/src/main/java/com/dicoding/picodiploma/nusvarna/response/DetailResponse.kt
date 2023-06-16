package com.dicoding.picodiploma.nusvarna.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailResponse(

	@field:SerializedName("detail")
	val detail: Detail,

	@field:SerializedName("predicted_label")
	val predictedLabel: String
): Parcelable

@Parcelize
data class Detail(

	@field:SerializedName("detail_suku")
	val detailSuku: String,

	@field:SerializedName("penggunaan")
	val penggunaan: String,

	@field:SerializedName("sejarah")
	val sejarah: String,

	@field:SerializedName("bahan_motif")
	val bahanMotif: String,

	@field:SerializedName("musik")
	val musik: String,

	@field:SerializedName("jenis_kelamin")
	val jenisKelamin: String,

	@field:SerializedName("nama_baju")
	val namaBaju: String,

	@field:SerializedName("asal_daerah")
	val asalDaerah: String
): Parcelable
