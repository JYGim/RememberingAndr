package kr.co.felici.rememberingtest.domain

import com.google.gson.annotations.SerializedName


data class BoardImageDTO(

    @SerializedName("id")
    public val id: Long,
    @SerializedName("path")
    public val path: String,
    @SerializedName("originalFilename")
    private val originalFilename: String,
    @SerializedName("fileSize")
    private val fileSize: Long,

    @SerializedName("letter")
    private val letter: LetterDTO,
    @SerializedName("memorialPost")
    private val memorialPost: MemorialPostDTOList

)
