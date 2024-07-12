package com.android.solidmvvmarchitecture.application

import android.app.Application
import android.util.Log
import com.android.solidmvvmarchitecture.apiclient.QuoteApiService
import com.android.solidmvvmarchitecture.apiclient.RetrofitHelper
import com.android.solidmvvmarchitecture.repository.QuoteRepository
import com.android.solidmvvmarchitecture.room.MainDatabase


class QuoteApplication : Application() {
    lateinit var quoteRepository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteApiService = RetrofitHelper.getInstance().create(QuoteApiService::class.java)
        val mainDatabase = MainDatabase.getDatabase(applicationContext)
        quoteRepository = QuoteRepository(quoteApiService, mainDatabase, applicationContext)
    }
}
