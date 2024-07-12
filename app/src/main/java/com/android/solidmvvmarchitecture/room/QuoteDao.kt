package com.android.solidmvvmarchitecture.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.solidmvvmarchitecture.models.Result

@Dao
interface QuoteDao {
    @Insert
    suspend fun addQuote(quote : Result)

    @Query("select * from quotes")
    suspend fun fetchAllQuotes() : List<Result>

}