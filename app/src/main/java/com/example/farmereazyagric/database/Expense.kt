package com.example.farmereazyagric.database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.farmereazyagric.models.DomainExpense
import com.example.farmereazyagric.models.DomainIncome

@Entity(tableName = "expense_table")
data class Expense(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "expense_id")
    var id: Int?=null,

    @ColumnInfo(name = "amount")
    var amount: Double=0.0 ,

    @ColumnInfo(name = "description")
    var description: String="" ,

    @ColumnInfo(name = "onWhat")
    var onWhat: String=""


)

fun Expense.asDomainModel(): DomainExpense {
    return DomainExpense(
        this.id.toString(),
        this.amount.toString(),
        this.description,
        this.onWhat
    )

}