package com.example.farmereazyagric.database

import androidx.room.*

@Dao
interface IncomeDao {

    @Insert
    fun insertIncome(income: Income)

    @Update
    fun updateIncome(income: Income)

    @Delete
    fun deleteIncome(income: Income)

    @Query("SELECT * FROM income_table WHERE income_id = :incomeId")
    fun getIncomeById(incomeId: Int): Income?

    @Query("SELECT * FROM income_table")
    fun getAllIncomes(): List<Income>



}