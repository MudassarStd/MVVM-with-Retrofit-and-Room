package com.android.solidmvvmarchitecture.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val quoteId : Long,
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,)
//    val tags: List<String>)

//data class ResultApi(
//    val _id: String,
//    val author: String,
//    val authorSlug: String,
//    val content: String,
//    val dateAdded: String,
//    val dateModified: String,
//    val length: Int,
//    val tags: List<String>
//)