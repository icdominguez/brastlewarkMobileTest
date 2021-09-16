package com.example.brastlewarkmobiletest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.brastlewarkmobiletest.domain.Inhabitant

@Dao
interface InhabitantDao {
    @Query("SELECT * from inhabitants")
    fun getAll(): List<Inhabitant>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(inhabitant: Inhabitant)
}