package kr.co.felici.rememberingtest.domain

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class MemorialPostDTO(

    @SerializedName("id")
    val id: Long,

    @SerializedName("writer")
    val writer: String,

    @SerializedName("contents")
    val contents: String,

    @SerializedName("pw")
    val pw: String,

//    @SerializedName("postedAt")
//    val postedAt: LocalDateTime,
//
//    @SerializedName("modifiedAt")
//    val modifiedAt: LocalDateTime,

    @SerializedName("images")
    val images: List<BoardImageDTO>?,

    @SerializedName("videos")
    val videos: List<BoardVideoDTO>?


)
