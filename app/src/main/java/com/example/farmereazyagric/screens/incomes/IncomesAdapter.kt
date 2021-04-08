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

class IncomesAdapter(val clickListener : IncomeListener):
    ListAdapter<Income,IncomesAdapter.ViewHolder>(IncomeDiffCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }


    class TextViewHolder(view: View): RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.income_item, parent, false)
                return TextViewHolder(view)
            }
        }
    }


    class ViewHolder private constructor(val binding: IncomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Income,
            clickListener: IncomeListener
        ) {
            //format the data to display from here
            binding.amount.text = item.amount.toString()
            binding.description.text = item.description
            binding.income = item
            binding.clickListener = clickListener
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

class IncomeListener(val clickListener: (transactionId: Int) -> Unit) {
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