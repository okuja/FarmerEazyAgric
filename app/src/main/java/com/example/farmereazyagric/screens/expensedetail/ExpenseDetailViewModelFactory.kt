package com.example.farmereazyagric.screens.expensedetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ExpenseDetailViewModelFactory (
    private val application: Application,
    private val id:Int
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ExpenseDetailViewModel::class.java)){
            return ExpenseDetailViewModel(application,id) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}