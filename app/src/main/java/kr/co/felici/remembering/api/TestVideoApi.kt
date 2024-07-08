package kr.co.felici.remembering.api

import kr.co.felici.remembering.dto.TestVideoDto
import kr.co.felici.remembering.dto.TestVideoDtoList
import kr.co.felici.remembering.model.TestVideoModel
import kr.co.felici.rememberingtest.domain.AlbumDTOList
import kr.co.felici.rememberingtest.domain.AlbumVideoDTOList
import kr.co.felici.rememberingtest.domain.MemorialPostDTOList
import retrofit2.Call
import retrofit2.http.GET

interface TestVideoApi {

    @GET("/v3/72a762d9-e622-43fd-87f6-6aa7f147e261")
    fun listVideos(): Call<TestVideoDto>

    @GET("/v3/72a762d9-e622-43fd-87f6-6aa7f147e261")
    fun getTestVideos(): Call<TestVideoDtoList>

}