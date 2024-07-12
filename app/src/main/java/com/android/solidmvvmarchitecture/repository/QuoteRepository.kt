package com.android.solidmvvmarchitecture.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.solidmvvmarchitecture.apiclient.QuoteApiService
import com.android.solidmvvmarchitecture.models.Quotes
import com.android.solidmvvmarchitecture.models.Result
import com.android.solidmvvmarchitecture.room.MainDatabase
import com.android.solidmvvmarchitecture.utils.NetworkUtils

class QuoteRepository(
    private val quoteApiService: QuoteApiService,
    private val mainDatabase: MainDatabase,
    private val application: Context
) {

    private var _quotes = MutableLiveData<Quotes>()
    val quotes: LiveData<Quotes> get() = _quotes

    suspend fun getQuotes(page: Int) {
        try {
            if (NetworkUtils.isInternetAvailable(application)) {

                val quotesResponse = quoteApiService.getQuotes(page)

                if (quotesResponse.body() != null) {
                    _quotes.postValue(quotesResponse.body()) // updating UI state

                    // persisting data
                    quotesResponse.body()!!.results.forEach {
                        mainDatabase.quotesDao().addQuote(it)
                    }
                }
            } else {

                val quoteFromDb = mainDatabase.quotesDao().fetchAllQuotes()
                // offline mode
                _quotes.postValue(
                    Quotes(0, 0, 0, quoteFromDb, 0, 0)
                )
            }
        } catch (e: Exception) {
            Log.e("QuoteRepository", "Error fetching quotes ${e.message}")
            // Handle the exception as needed (e.g., show a message to the user)
        }
    }
}
