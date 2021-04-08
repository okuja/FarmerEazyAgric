package com.example.farmereazyagric.screens.incomes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.farmereazyagric.R
import com.example.farmereazyagric.databinding.FragmentIncomeBinding


class IncomeFragment : Fragment() {

    private lateinit var incomeViewModel: IncomeViewModel
    private lateinit var incomeViewModelFactoy: IncomeViewModelFactoy

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

        incomeViewModelFactoy = IncomeViewModelFactoy(application)

        incomeViewModel =  ViewModelProviders.of(this,incomeViewModelFactoy).get(IncomeViewModel::class.java)

        val adapter = IncomesAdapter(IncomeListener { id ->
            incomeViewModel.onItemClicked(id)
        })

        binding.recyclerViewIncome.adapter = adapter

        binding.setLifecycleOwner(viewLifecycleOwner)
        binding.viewModel = incomeViewModel

        return binding.root
    }
}

