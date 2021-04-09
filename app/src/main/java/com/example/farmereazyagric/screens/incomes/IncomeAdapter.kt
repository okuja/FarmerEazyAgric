package com.example.farmereazyagric.screens.incomes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.farmereazyagric.R
import com.example.farmereazyagric.database.Income
import com.example.farmereazyagric.databinding.IncomeItemBinding

class IncomeAdapter(val clickListener : IncomeListener,val clickDeleteListener : IncomeListener):
    ListAdapter<Income,IncomeAdapter.ViewHolder>(IncomeDiffCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener,clickDeleteListener)
    }


    class ViewHolder private constructor(val binding: IncomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Income,
            clickListener: IncomeListener,
            clickDeleteListener: IncomeListener
        ) {
            //format the data to display from here
            // if expenses show red if incomes show green
            binding.amount.text = item.amount.toString()
            binding.description.text = item.description
            binding.from.text = item.fromWho
            binding.income = item
            binding.clickListener = clickListener
            binding.clickDeleteListener = clickDeleteListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IncomeItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class IncomeListener(val clickListener: (incomeId: Int) -> Unit) {
    fun onClick(income: Income) = clickListener(income.id!!)
}


//implementing diffutil
class IncomeDiffCallback : DiffUtil.ItemCallback<Income>() {
    override fun areItemsTheSame(oldItem: Income, newItem: Income): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Income, newItem: Income): Boolean {
        return oldItem == newItem
    }
}