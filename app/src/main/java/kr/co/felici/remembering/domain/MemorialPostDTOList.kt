package kr.co.felici.rememberingtest.domain

import com.google.gson.annotations.SerializedName

data class MemorialPostDTOList(

    @SerializedName("posts")
    val posts: List<MemorialPostDTO> = arrayListOf()


)
