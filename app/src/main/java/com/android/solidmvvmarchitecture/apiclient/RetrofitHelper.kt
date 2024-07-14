package com.android.solidmvvmarchitecture.apiclient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

//    val  BASE_URL = "https://quotable.io"

//    private const val BASE_URL = "https://quotable.io/"
    private const val BASE_URL = "https://api.api-ninjas.com"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}