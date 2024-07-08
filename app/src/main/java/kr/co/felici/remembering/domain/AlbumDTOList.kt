package kr.co.felici.rememberingtest.domain

import com.google.gson.annotations.SerializedName

data class AlbumDTOList(

    @SerializedName("photos")
    val photos: List<AlbumDTO> = arrayListOf(),

//    @SerializedName("urlList")
//    val urlList: List<String> = arrayListOf()

//    @SerializedName("basePath")
//    val basePath: String = ""




//    @SerializedName("data")
//public List<Datum> data = new ArrayList();
//
//public class Datum {
//
//    @SerializedName("id")
//    public Integer id;
//    @SerializedName("first_name")
//    public String first_name;
//    @SerializedName("last_name")
//    public String last_name;
//    @SerializedName("avatar")
//    public String avatar;
//
//}

)
