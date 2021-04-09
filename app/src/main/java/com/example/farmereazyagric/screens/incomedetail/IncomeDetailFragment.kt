package com.example.farmereazyagric.screens.incomedetail

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
import com.example.farmereazyagric.databinding.FragmentIncomeDetailBinding
import com.example.farmereazyagric.screens.incomes.*

class IncomeDetailFragment : Fragment() {
    private lateinit var incomeDetailViewModel: IncomeDetailViewModel
    private lateinit var incomeDetailViewModelFactory: IncomeDetailViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding:FragmentIncomeDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_income_detail,
            container, false)

        val application = requireNotNull(this.activity).application

        val args = IncomeDetailFragmentArgs.fromBundle(requireArguments())

        Log.i("Income Detail Fragment",args.incomeId.toString())

        incomeDetailViewModelFactory = IncomeDetailViewModelFactory(application,args.incomeId)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        incomeDetailViewModel =  ViewModelProvider(this,incomeDetailViewModelFactory).get(IncomeDetailViewModel::class.java)

        binding.viewModel = incomeDetailViewModel

        // Specify the current activity as the lifecycle owner of the binding.
        // This is necessary so that the binding can observe LiveData updates.
        binding.setLifecycleOwner(this)

        incomeDetailViewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {income ->
            income ?.let{
                this.findNavController().navigate(
                    IncomeDetailFragmentDirections.actionIncomeDetailFragmentToHomeFragment()
                )
                incomeDetailViewModel.onDoneNavigatingToDetail()
            }
        })


        return binding.root
    }

}
