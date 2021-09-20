package com.example.brastlewarkmobiletest.network

import com.example.brastlewarkmobiletest.common.Utils
import com.example.brastlewarkmobiletest.domain.GetInhabitantsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {

    @GET("data.json")
    suspend fun getAllInhabitants() : Response<GetInhabitantsResponse>


    companion object {
        var service: Service? = null

        fun getInstance() : Service {
            if(service == null) {
                val retrofit = Retrofit.Builder().baseUrl (Utils.Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
                service = retrofit.create(Service::class.java)
            }
            return service!!
        }
    }
}