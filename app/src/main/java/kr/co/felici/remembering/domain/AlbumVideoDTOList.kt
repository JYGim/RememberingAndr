package kr.co.felici.rememberingtest.domain

import com.google.gson.annotations.SerializedName

data class AlbumVideoDTOList(

    @SerializedName("videos")
    val videos: List<AlbumDTO> = arrayListOf()
)
