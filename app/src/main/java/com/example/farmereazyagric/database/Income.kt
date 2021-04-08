package com.example.farmereazyagric.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "income_table")
data class Income(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "income_id")
    var id: Int,

    @ColumnInfo(name = "amount")
    val amount: Double ,

    @ColumnInfo(name = "description")
    val description: String="" ,

    @ColumnInfo(name = "fromWho")
    val fromWho: String=""


)