package com.example.farmereazyagric.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.farmereazyagric.database.AppDatabase
import com.example.farmereazyagric.database.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExpensesRepository (private val database : AppDatabase) {

    val expenses: LiveData<List<Expense>> = database.expenseDao.getAllExpenses()


    suspend fun getAllExpenses(){
        withContext(Dispatchers.IO){
            database.expenseDao.getAllExpenses()
        }
    }

    suspend fun insertExpenses(expense: Expense){
        Log.i("Repository",expense.description)
        Log.i("Repository",expense.amount.toString())
        withContext(Dispatchers.IO){
            database.expenseDao.insertExpense(expense)
        }
    }

    suspend fun deleteExpense(id :Int){
        withContext(Dispatchers.IO){
            database.expenseDao.deleteExpense(id)
        }
    }

    suspend fun updateExpense(expense: Expense){
        withContext(Dispatchers.IO){
            Log.i(" Update Repository",expense.description)
            Log.i(" Update Repository",expense.amount.toString())
            database.expenseDao.updateExpense(expense)
        }
    }

    suspend fun getExpenseById(expenseId:Int): Expense?{
        return withContext(Dispatchers.IO){
            database.expenseDao.getExpenseById(expenseId)
        }
    }

}