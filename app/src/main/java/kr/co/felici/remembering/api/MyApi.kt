package kr.co.felici.remembering.api

import kr.co.felici.remembering.dto.TestVideoDto
import kr.co.felici.rememberingtest.domain.AlbumDTOList
import kr.co.felici.rememberingtest.domain.AlbumVideoDTOList
import kr.co.felici.rememberingtest.domain.MemorialPostDTOList
import retrofit2.Call
import retrofit2.http.GET

interface MyApi {

    @GET("/api/album/photos")
    fun getAlbumPhotos(): Call<AlbumDTOList>

    @GET("/api/album/videos")
    fun getAlbumVideos(): Call<AlbumVideoDTOList>

//    @GET("/api/album/photo/{photo}")
//    fun getPhoto(@Path("photo") photo: String): Call<Byte>

    @GET("/api/board/memorial-posts")
    fun getAllPosts(): Call<MemorialPostDTOList>







//    @GET("/api/album/photos")
//    fun getAllPhotos(): Call<List<AlbumPhoto>>

}