package com.example.farmereazyagric.screens.expenses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.farmereazyagric.database.AppDatabase
import com.example.farmereazyagric.repositories.ExpensesRepository
import kotlinx.coroutines.*

class ExpenseViewModel(application: Application) :AndroidViewModel(application) {

    private val expensesRepository = ExpensesRepository(AppDatabase.getInstance(application))

    val expenses = expensesRepository.expenses

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        refreshDataFromRepository()
    }

    private val _navigateToDetail = MutableLiveData<Int>()
    val navigateToDetail: LiveData<Int>
        get() = _navigateToDetail

    private val _selectedItemId = MutableLiveData<Int>()
    val selectedItemId: LiveData<Int>
        get() = _selectedItemId


    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            expensesRepository.getAllExpenses()
        }
    }

    fun deleteDataFromRepository(id :Int){
        uiScope.launch {
            withContext(Dispatchers.IO){
                expensesRepository.deleteExpense(id)
            }
        }
    }

    fun onItemClicked(id:Int){
        _navigateToDetail.value = id
    }

    fun onDoneNavigatingToDetail(){
        _navigateToDetail.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}