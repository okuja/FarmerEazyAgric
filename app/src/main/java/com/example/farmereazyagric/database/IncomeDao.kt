package com.example.farmereazyagric.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface IncomeDao {

    @Insert
    fun insertIncome(income: Income)

    @Update
    fun updateIncome(income: Income)

    @Query("DELETE FROM income_table WHERE  income_id = :id")
    fun deleteIncome(id :Int)

    @Query("SELECT * FROM income_table WHERE income_id = :incomeId")
    fun getIncomeById(incomeId: Int): Income?

    @Query("SELECT * FROM income_table")
    fun getAllIncomes(): LiveData<List<Income>>



}