package com.example.farmereazyagric.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ExpenseDao {

    @Insert
    fun insertExpense(expense: Expense)

    @Update
    fun updateExpense(expense: Expense)

    @Query("DELETE FROM expense_table WHERE  expense_id = :id")
    fun deleteExpense(id: Int)

    @Query("SELECT * FROM expense_table WHERE expense_id = :expenseId")
    fun getExpenseById(expenseId: Int): Expense?

    @Query("SELECT * FROM expense_table")
    fun getAllExpenses(): LiveData<List<Expense>>

}