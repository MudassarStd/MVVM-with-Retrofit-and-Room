package com.android.solidmvvmarchitecture.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.solidmvvmarchitecture.apiclient.QuoteApiService
import com.android.solidmvvmarchitecture.models.Quotes
import com.android.solidmvvmarchitecture.room.MainDatabase
import com.android.solidmvvmarchitecture.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepository(
    private val quoteApiService: QuoteApiService,
    private val mainDatabase: MainDatabase,
    private val application: Context
) {
    private val databaseDao = mainDatabase.quotesDao()
    private val _quotes = MutableLiveData<Response<Quotes>>()
    val quotes: LiveData<Response<Quotes>> get() = _quotes

    suspend fun getQuotes(page: Int) = withContext(Dispatchers.IO) {
        _quotes.postValue(Response.Loading())
        try {
            if (NetworkUtils.isInternetAvailable(application)) {
                fetchAndCacheQuotes(page)
            } else {
                loadQuotesFromDatabase()
            }
        } catch (e: Exception) {
            _quotes.postValue(Response.Error(e.message ?: "Unknown error"))
        }
    }

    private suspend fun fetchAndCacheQuotes(page: Int) {
        val quotesResponse = quoteApiService.getQuotes(page)
        if (quotesResponse.isSuccessful) {
            val responseBody = quotesResponse.body()
            if (responseBody != null) {
                _quotes.postValue(Response.Success(responseBody))
                responseBody.results.forEach {
                    databaseDao.addQuote(it)
                }
            } else {
                _quotes.postValue(Response.Error("Response body is null"))
            }
        } else {
            _quotes.postValue(Response.Error("Response not successful: ${quotesResponse.code()}"))
        }
    }

    private suspend fun loadQuotesFromDatabase() {
        val quoteFromDb = databaseDao.fetchAllQuotes()
        _quotes.postValue(Response.Success(Quotes(0,0, 0, quoteFromDb, 0, 0)))
    }
}



