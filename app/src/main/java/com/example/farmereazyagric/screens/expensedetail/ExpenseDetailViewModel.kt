package com.example.farmereazyagric.screens.expensedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.farmereazyagric.database.AppDatabase
import com.example.farmereazyagric.database.Expense
import com.example.farmereazyagric.database.asDomainModel
import com.example.farmereazyagric.models.DomainExpense
import com.example.farmereazyagric.repositories.ExpensesRepository
import kotlinx.coroutines.*

class ExpenseDetailViewModel (application: Application, id: Int) : AndroidViewModel(application) {
    /**
     * The data source this ViewModel will fetch results from.
     */
    private val expensesRepository = ExpensesRepository(AppDatabase.getInstance(application))

    val expenseId = id

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


    private val _item = MutableLiveData<DomainExpense?>()
    val item: LiveData<DomainExpense?>
        get() = _item


    private  fun getItem(id:Int) {
        viewModelScope.launch {
            val expense = expensesRepository.getExpenseById(id)
            _item.value = expense?.asDomainModel()
        }
    }

    fun updateDataFromRepository(amount:String,reason:String,onWhat:String){
        uiScope.launch{
            val newExpense = Expense()
            newExpense.amount = amount.toDouble()
            newExpense.description = reason
            newExpense.onWhat = onWhat
            newExpense.id=expenseId

            withContext(Dispatchers.IO){
                expensesRepository.updateExpense(newExpense)
            }
        }
        _navigateToDetail.value = expenseId
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