package com.example.farmereazyagric.screens.incomedetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class IncomeDetailViewModelFactory (
    private val application: Application,
    private val id:Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(IncomeDetailViewModel::class.java)){
            return IncomeDetailViewModel(application,id) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}