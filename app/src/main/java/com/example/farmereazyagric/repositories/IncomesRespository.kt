package com.example.farmereazyagric.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.farmereazyagric.database.AppDatabase
import com.example.farmereazyagric.database.Income
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class IncomesRepository(private val database : AppDatabase) {

//    val incomes: List<Income> = database.incomeDao.getAllIncomes()

    suspend fun getAllIncomes(){
        withContext(Dispatchers.IO){
            database.incomeDao.getAllIncomes()
        }
    }

    suspend fun insertIncomes(income:Income){
        withContext(Dispatchers.IO){
            database.incomeDao.insertIncome(income)
        }
    }

    suspend fun deleteIncome(income:Income){
        withContext(Dispatchers.IO){
            database.incomeDao.deleteIncome(income)
        }
    }

    suspend fun updateIncome(income:Income){
        withContext(Dispatchers.IO){
            database.incomeDao.updateIncome(income)
        }
    }

    suspend fun getIncomeById(incomeId:Int){
        withContext(Dispatchers.IO){
            database.incomeDao.getIncomeById(incomeId)
        }
    }

}