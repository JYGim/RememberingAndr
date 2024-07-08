package kr.co.felici.remembering.dto

import com.google.gson.annotations.SerializedName
import kr.co.felici.remembering.model.TestVideoModel

data class TestVideoDto(
//    @SerializedName("videos")
    val videos: List<TestVideoModel>
)
