package com.example.test
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RegisterService {

    @POST("/register")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun post_users(
        @Body jsonparams: PostModel
    ): Call<PostResult>

    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        private const val BASE_URL = "http://192.168.0.5:80" // 주소

        fun create(): RegisterService {

            val gson :Gson =   GsonBuilder().setLenient().create();

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RegisterService::class.java)
        }
    }
}