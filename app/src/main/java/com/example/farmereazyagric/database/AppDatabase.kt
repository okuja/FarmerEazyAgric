package com.example.farmereazyagric.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Income::class,Expense::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Connects the user to the DAO.
     * can have many dao
     */
    abstract val expenseDao: ExpenseDao
    abstract val incomeDao: IncomeDao

    companion object{

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}