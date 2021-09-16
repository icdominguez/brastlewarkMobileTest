package com.example.brastlewarkmobiletest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.brastlewarkmobiletest.repository.InhabitantsRepository
import java.lang.IllegalArgumentException

class HomeViewModelFactory constructor(private val repository: InhabitantsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}