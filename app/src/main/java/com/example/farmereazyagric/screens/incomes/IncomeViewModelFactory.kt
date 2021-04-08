package com.example.farmereazyagric.screens.incomes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class IncomeViewModelFactoy (
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(IncomeViewModel::class.java)){
            return IncomeViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}