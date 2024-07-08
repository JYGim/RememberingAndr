package kr.co.felici.rememberingtest.domain

import com.google.gson.annotations.SerializedName

data class AlbumDTO(

    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String?,

    @SerializedName("info")
    val info: String?,

    @SerializedName("descriptions")
    val descriptions: String?,

    @SerializedName("photo")
    val photo: String?,

    @SerializedName("video")
    val video: String?,

    @SerializedName("isWhenPictureWasTakenclear")
    val isWhenPictureWasTakenclear: String?,

    @SerializedName("estimatedYear")
    val estimatedYear: String?,

//    @SerializedName("tookIn")
//    val tookIn: LocalDate?,

    @SerializedName("displayOrder")
    val displayOrder: Int

)
