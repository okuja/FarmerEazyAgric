package com.example.farmereazyagric.screens.addincomes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.farmereazyagric.screens.incomes.IncomeViewModel

class AddIncomesViewModelFactory (
        private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddIncomesViewModel::class.java)){
            return AddIncomesViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}