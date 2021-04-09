package com.example.farmereazyagric.screens.addincomes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.farmereazyagric.database.AppDatabase
import com.example.farmereazyagric.database.Income
import com.example.farmereazyagric.repositories.IncomesRepository
import kotlinx.coroutines.*

class AddIncomesViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * The data source this ViewModel will fetch results from.
     */
    private val incomesRepository = IncomesRepository(AppDatabase.getInstance(application))

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun addIncome(amount:String,reason:String,fromWho:String){
        uiScope.launch{
            val newIncome = Income()
            newIncome.amount = amount.toDouble()
            newIncome.description = reason
            newIncome.fromWho = fromWho

            withContext(Dispatchers.IO){
                incomesRepository.insertIncomes(newIncome)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}