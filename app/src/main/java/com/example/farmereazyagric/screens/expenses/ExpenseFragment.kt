package com.example.farmereazyagric.screens.expenses

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmereazyagric.R
import com.example.farmereazyagric.databinding.FragmentExpenseBinding


class ExpenseFragment : Fragment() {

    private lateinit var viewModel: ExpenseViewModel
    private lateinit var viewModelFactory: ExpenseViewModelFactory


    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding:FragmentExpenseBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_expense,
            container,
            false)


        val application = requireNotNull(this.activity).application

        viewModelFactory = ExpenseViewModelFactory(application)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        viewModel =  ViewModelProvider(this,viewModelFactory).get(ExpenseViewModel::class.java)

        binding.viewModel = viewModel

        val adapter = ExpenseAdapter(ExpenseListener { id ->
            viewModel.onItemClicked(id)
        }, ExpenseListener { id ->
            viewModel.deleteDataFromRepository(id)
        })

        binding.recyclerViewExpenses.adapter = adapter

        Log.i("Expense Out Adapter", viewModel.expenses.value?.get(0)?.onWhat.toString())
        //get data into the adapter
        viewModel.expenses.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.i("Expense In Adapter",it[0].id.toString())
                adapter.submitList(it)
            }
        })

        // Specify the current activity as the lifecycle owner of the binding.
        // This is necessary so that the binding can observe LiveData updates.
        binding.setLifecycleOwner(this)

        viewModel.navigateToDetail.observe(this, Observer {expense ->
            expense ?.let{
                this.findNavController().navigate(
                    ExpenseFragmentDirections.actionExpenseFragmentToExpenseDetailFragment(expense)
                )
                viewModel.onDoneNavigatingToDetail()
            }
        })


        val layoutManager = LinearLayoutManager(context)
        binding.recyclerViewExpenses.layoutManager = layoutManager


        return binding.root
    }

    
}