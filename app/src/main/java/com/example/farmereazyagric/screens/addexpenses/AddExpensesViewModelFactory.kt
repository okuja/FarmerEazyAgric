package com.example.farmereazyagric.screens.addexpenses

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddExpensesViewModelFactory (
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddExpensesViewModel::class.java)){
            return AddExpensesViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}