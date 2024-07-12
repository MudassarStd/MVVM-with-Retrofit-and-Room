package com.android.solidmvvmarchitecture.room
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.solidmvvmarchitecture.models.Result


@Database(entities = [Result::class], version = 1)
abstract class MainDatabase : RoomDatabase() {

    abstract fun quotesDao() : QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "main_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}