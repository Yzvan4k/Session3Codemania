package com.example.session3codemania

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface API {
    @GET("/catalog/corses")
    fun getCourses():Call<List<CoursesData>>

    @GET("/catalog/tags")
    fun getTags(): Call<List<TagsData>>

    @POST("media")
    fun getImage(@Header("filename") filename:String):Call<ResponseBody>
}