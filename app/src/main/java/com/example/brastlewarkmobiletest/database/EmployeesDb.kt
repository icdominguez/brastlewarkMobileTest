package com.example.brastlewarkmobiletest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.brastlewarkmobiletest.domain.Inhabitant

@Database(entities = [Inhabitant::class], version = 1)
@TypeConverters(Converter::class)
abstract class InhabitantsDb : RoomDatabase() {
    abstract fun inhabitantDao() : InhabitantDao

    companion object {
        private val DATABASE_NAME = "inhabitants_database"
        @Volatile
        private var INSTANCE: InhabitantsDb? = null

        fun getInstance(context: Context) : InhabitantsDb? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, InhabitantsDb::class.java, DATABASE_NAME).build()
            }
            return INSTANCE
        }
    }
}