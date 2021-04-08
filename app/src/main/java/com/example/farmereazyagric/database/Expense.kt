package com.example.farmereazyagric.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense_table")
data class Expense(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "expense_id")
    var id: Int,

    @ColumnInfo(name = "amount")
    val amount: Double ,

    @ColumnInfo(name = "description")
    val description: String="" ,

    @ColumnInfo(name = "onWhat")
    val onWhat: String=""


)