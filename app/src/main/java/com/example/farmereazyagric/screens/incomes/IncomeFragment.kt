package com.example.farmereazyagric.screens.incomes

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farmereazyagric.R
import com.example.farmereazyagric.databinding.FragmentIncomeBinding


class IncomeFragment : Fragment() {

    private lateinit var incomeViewModel: IncomeViewModel
    private lateinit var incomeViewModelFactory: IncomeViewModelFactory

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:FragmentIncomeBinding =  DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_income,
            container,
            false)

        val application = requireNotNull(this.activity).application

        incomeViewModelFactory = IncomeViewModelFactory(application)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        incomeViewModel =  ViewModelProvider(this,incomeViewModelFactory).get(IncomeViewModel::class.java)

        binding.viewModel = incomeViewModel

        val adapter = IncomeAdapter(IncomeListener { id ->
            incomeViewModel.onItemClicked(id)
        },IncomeListener { id ->
            incomeViewModel.deleteDataFromRepository(id)
        })

        binding.recyclerViewIncome.adapter = adapter

        //get data into the adapter
        incomeViewModel.incomes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        // Specify the current activity as the lifecycle owner of the binding.
        // This is necessary so that the binding can observe LiveData updates.
        binding.setLifecycleOwner(this)

        incomeViewModel.navigateToDetail.observe(this, Observer {income ->
            income ?.let{
                this.findNavController().navigate(
                        IncomeFragmentDirections.actionIncomeFragmentToIncomeDetailFragment(income)
                )
                incomeViewModel.onDoneNavigatingToDetail()
            }
        })


        val layoutManager = LinearLayoutManager(context)
        binding.recyclerViewIncome.layoutManager = layoutManager

        return binding.root
    }
}

