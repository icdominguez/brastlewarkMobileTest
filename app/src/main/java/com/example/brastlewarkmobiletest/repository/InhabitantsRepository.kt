package com.example.brastlewarkmobiletest.repository

import com.example.brastlewarkmobiletest.common.MyApp
import com.example.brastlewarkmobiletest.domain.Inhabitant
import com.example.brastlewarkmobiletest.database.InhabitantDao
import com.example.brastlewarkmobiletest.database.InhabitantsDb
import com.example.brastlewarkmobiletest.network.Service

class InhabitantsRepository constructor(private val service: Service) {

    private val inhabitantDao: InhabitantDao? = InhabitantsDb.getInstance(MyApp.applicationContext())?.inhabitantDao()

    suspend fun getAllHabitants() = service.getAllInhabitants()

    fun insert(inhabitant: Inhabitant) {
        if(inhabitant != null) inhabitantDao?.insert(inhabitant)
    }
}