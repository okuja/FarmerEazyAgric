package com.example.farmereazyagric.screens.expenses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.farmereazyagric.database.Expense
import com.example.farmereazyagric.databinding.ExpenseItemBinding

class ExpenseAdapter(val clickListener : ExpenseListener,val clickDeleteListener : ExpenseListener):
    ListAdapter<Expense, ExpenseAdapter.ViewHolder>(ExpenseDiffCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener,clickDeleteListener)
    }


    class ViewHolder private constructor(val binding: ExpenseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Expense,
            clickListener: ExpenseListener,
            clickDeleteListener: ExpenseListener
        ) {
            //format the data to display from here
            // if expenses show red if incomes show green
            binding.amount.text = item.amount.toString()
            binding.description.text = item.description
            binding.from.text = item.onWhat
            binding.expense = item
            binding.clickListener = clickListener
            binding.clickDeleteListener = clickDeleteListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ExpenseItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class ExpenseListener(val clickListener: (expenseId: Int) -> Unit) {
    fun onClick(expense: Expense) = clickListener(expense.id!!)
}


//implementing diffutil
class ExpenseDiffCallback : DiffUtil.ItemCallback<Expense>() {
    override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
        return oldItem == newItem
    }
}