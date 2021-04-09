package com.example.farmereazyagric.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.farmereazyagric.models.DomainIncome

@Entity(tableName = "income_table")
data class Income(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "income_id")
    var id:Int? = null,

    @ColumnInfo(name = "amount")
    var amount: Double = 0.0,

    @ColumnInfo(name = "description")
    var description: String="" ,

    @ColumnInfo(name = "fromWho")
    var fromWho: String=""
)

fun Income.asDomainModel(): DomainIncome {
    return DomainIncome(
        this.id.toString(),
        this.amount.toString(),
        this.description,
        this.fromWho
    )

}
