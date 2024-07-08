package kr.co.felici.remembering.dto

import com.google.gson.annotations.SerializedName
import kr.co.felici.remembering.model.TestVideoModel
import kr.co.felici.rememberingtest.domain.MemorialPostDTO

data class TestVideoDtoList(
//    @SerializedName("videos")
    val videos: List<TestVideoModel> = arrayListOf()

)
