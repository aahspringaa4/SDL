package com.example.mealproject

import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory


object ApiProvider {
    private var instance: Retrofit?=null
    private val BASE_URL = "http://api.potatochips.live"

    fun getInstance(): Retrofit{
        if(instance == null) {
            instance = Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}