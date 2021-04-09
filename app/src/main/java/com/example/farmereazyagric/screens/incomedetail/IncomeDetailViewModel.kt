package com.example.farmereazyagric.screens.incomedetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.farmereazyagric.database.AppDatabase
import com.example.farmereazyagric.database.Income
import com.example.farmereazyagric.database.asDomainModel
import com.example.farmereazyagric.models.DomainIncome
import com.example.farmereazyagric.repositories.IncomesRepository
import kotlinx.coroutines.*

class IncomeDetailViewModel (application: Application,id: Int) : AndroidViewModel(application) {
    /**
     * The data source this ViewModel will fetch results from.
     */
    private val incomesRepository = IncomesRepository(AppDatabase.getInstance(application))

    val incomeId = id

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
         getItem(id)
    }

    private val _navigateToDetail = MutableLiveData<Int>()
    val navigateToDetail: LiveData<Int>
        get() = _navigateToDetail

    private val _selectedItemId = MutableLiveData<Int>()
    val selectedItemId: LiveData<Int>
        get() = _selectedItemId


    private val _item = MutableLiveData<DomainIncome?>()
    val item: LiveData<DomainIncome?>
        get() = _item


    private  fun getItem(id:Int) {
        viewModelScope.launch {
            val income = incomesRepository.getIncomeById(id)
            _item.value = income?.asDomainModel()
        }
    }

    fun updateDataFromRepository(amount:String,reason:String,fromWho:String){
        uiScope.launch{
            val newIncome = Income()
            newIncome.amount = amount.toDouble()
            newIncome.description = reason
            newIncome.fromWho = fromWho
            newIncome.id=incomeId

            withContext(Dispatchers.IO){
                incomesRepository.updateIncome(newIncome)
            }
        }
        _navigateToDetail.value = incomeId
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