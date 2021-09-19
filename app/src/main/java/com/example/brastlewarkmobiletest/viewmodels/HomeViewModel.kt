package com.example.brastlewarkmobiletest.viewmodels

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brastlewarkmobiletest.common.MyApp
import com.example.brastlewarkmobiletest.repository.InhabitantsRepository
import com.example.brastlewarkmobiletest.domain.Inhabitant
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import java.lang.Exception

class HomeViewModel (private val mainRepository: InhabitantsRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    var inhabitantsList = MutableLiveData<ArrayList<Inhabitant>>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->  onError("There was an error: ${throwable.localizedMessage}")}
    val loading = MutableLiveData<Boolean>()

    fun getAllInhabitants() {

        // Check if there is connection available
        val networkInfo = (MyApp.applicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetwork


        // If there is connection getAll info from service and insert into local db
        if(networkInfo != null) {
            job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = mainRepository.getAllHabitants()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        var getInhabitantsResponse = response.body()
                        inhabitantsList.postValue(getInhabitantsResponse?.inhabitants)
                        loading.value = false

                    } else {
                        onError(response.message())
                        loading.value = false
                        Log.e("ICDOMINGUEZ", response.message())
                    }
                }
            }
        } else {
            loading.value = false
            viewModelScope.launch(Dispatchers.IO) {
                inhabitantsList.postValue(mainRepository.getAll() as ArrayList<Inhabitant>?)
            }
        }

    }

    fun insertInhabitant(inhabitant: Inhabitant) {

        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.insert(inhabitant)
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}