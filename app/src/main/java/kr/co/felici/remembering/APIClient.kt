package kr.co.felici.rememberingtest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


public class APIClient {

//    val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
//    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)


    companion object {
        private var retrofit: Retrofit? = null

//        val Base_URL = "http://10.0.2.2:8080/"

//        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        fun getClient(): Retrofit? =
            Retrofit.Builder()
                .baseUrl("http://remembering.website")
                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
                .build()

    }

}