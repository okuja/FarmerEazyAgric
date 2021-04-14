package com.example.farmereazyagric.screens.addexpenses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.farmereazyagric.database.AppDatabase
import com.example.farmereazyagric.database.Expense
import com.example.farmereazyagric.repositories.ExpensesRepository
import kotlinx.coroutines.*

class AddExpensesViewModel (application: Application) : AndroidViewModel(application){
    private val expensesRepository = ExpensesRepository(AppDatabase.getInstance(application))

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun addExpense(amount:String,reason:String,onWhat:String){
        uiScope.launch{
            val newItem = Expense()
            newItem.amount = amount.toDouble()
            newItem.description = reason
            newItem.onWhat = onWhat

            withContext(Dispatchers.IO){
                expensesRepository.insertExpenses(newItem)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}