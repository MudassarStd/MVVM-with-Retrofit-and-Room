package com.android.solidmvvmarchitecture.apiclient

import com.android.solidmvvmarchitecture.models.Quotes
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApiService {

    @GET("/quotes")
    suspend fun getQuotes( @Query("page") page : Int) : Response<Quotes>

//      @GET("/v1/quotes")
//    suspend fun getQuotes( @Query("category") category : String) : Response<SimpleQuote>



    // Base URL + /quotes? + page=1
    //            /get quotes from where page == 1
}