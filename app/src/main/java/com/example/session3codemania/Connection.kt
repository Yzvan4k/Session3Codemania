package com.example.session3codemania

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

lateinit var api : API
val TAG = "errror"

class Utils{
    fun setUpApi(){
        val retrofit = Retrofit.Builder()
            .baseUrl("http://95.31.130.149:8085/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(API::class.java)
    }
}