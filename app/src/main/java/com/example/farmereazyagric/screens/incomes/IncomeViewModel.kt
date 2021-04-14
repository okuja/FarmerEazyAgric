package com.example.farmereazyagric.screens.incomes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.farmereazyagric.database.AppDatabase
import com.example.farmereazyagric.database.Income
import com.example.farmereazyagric.repositories.IncomesRepository
import kotlinx.coroutines.*

class IncomeViewModel(application: Application) :AndroidViewModel(application) {
    /**
     * The data source this ViewModel will fetch results from.
     */
    private val incomesRepository = IncomesRepository(AppDatabase.getInstance(application))


    val incomes = incomesRepository.incomes

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
                incomesRepository.getAllIncomes()
        }
    }

    fun deleteDataFromRepository(id :Int){
        uiScope.launch {
            withContext(Dispatchers.IO){
                incomesRepository.deleteIncome(id)
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