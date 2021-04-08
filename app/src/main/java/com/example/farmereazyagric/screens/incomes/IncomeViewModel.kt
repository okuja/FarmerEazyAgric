package com.example.farmereazyagric.screens.incomes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.farmereazyagric.database.AppDatabase
import com.example.farmereazyagric.database.Income
import com.example.farmereazyagric.repositories.IncomesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext

class IncomeViewModel(application: Application) :AndroidViewModel(application) {
    /**
     * The data source this ViewModel will fetch results from.
     */
    private val incomesRepository = IncomesRepository(AppDatabase.getInstance(application))

    private val _incomes = MutableLiveData<List<Income>>()
    val incomes: LiveData<List<Income>>
        get() = _incomes

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private suspend fun addIncome(income: Income){
        return withContext(Dispatchers.IO){
           incomesRepository.insertIncomes(income)
        }
    }

    private val _navigateToDetail = MutableLiveData<Int>()
    val navigateToDetail: LiveData<Int>
        get() = _navigateToDetail


    fun onItemClicked(id:Int){
        _navigateToDetail.value = id
    }

    fun onDoneNavigatingToTransactionDetail(){
        _navigateToDetail.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}