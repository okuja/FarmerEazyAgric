package com.example.farmereazyagric.repositories


import android.util.Log
import androidx.lifecycle.LiveData
import com.example.farmereazyagric.database.AppDatabase
import com.example.farmereazyagric.database.Income
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class IncomesRepository(private val database : AppDatabase) {

    val incomes: LiveData<List<Income>> = database.incomeDao.getAllIncomes()


    suspend fun getAllIncomes(){
        withContext(Dispatchers.IO){
            database.incomeDao.getAllIncomes()
        }
    }

    suspend fun insertIncomes(income:Income){
        Log.i("Repository",income.description)
        Log.i("Repository",income.amount.toString())
        withContext(Dispatchers.IO){
            database.incomeDao.insertIncome(income)
        }
    }

    suspend fun deleteIncome(id :Int){
        withContext(Dispatchers.IO){
            database.incomeDao.deleteIncome(id)
        }
    }

    suspend fun updateIncome(income:Income){
        withContext(Dispatchers.IO){
            Log.i(" Update Repository",income.description)
            Log.i(" Update Repository",income.amount.toString())
            database.incomeDao.updateIncome(income)
        }
    }

    suspend fun getIncomeById(incomeId:Int):Income?{
       return withContext(Dispatchers.IO){
            database.incomeDao.getIncomeById(incomeId)
        }
    }

}