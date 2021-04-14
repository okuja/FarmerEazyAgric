package com.example.farmereazyagric.screens.expensedetail

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
import com.example.farmereazyagric.R
import com.example.farmereazyagric.databinding.FragmentExpenseDetailBinding

class ExpenseDetailFragment : Fragment() {

    private lateinit var viewModel: ExpenseDetailViewModel
    private lateinit var viewModelFactory: ExpenseDetailViewModelFactory


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding :FragmentExpenseDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_expense_detail,
            container, false)


        val application = requireNotNull(this.activity).application

        val args = ExpenseDetailFragmentArgs.fromBundle(requireArguments())

        Log.i("Expense Detail Fragment",args.expenseId.toString())

        viewModelFactory = ExpenseDetailViewModelFactory(application,args.expenseId)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        viewModel =  ViewModelProvider(this,viewModelFactory).get(
            ExpenseDetailViewModel::class.java)

        binding.viewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding.
        // This is necessary so that the binding can observe LiveData updates.
        binding.setLifecycleOwner(this)

        viewModel.navigateToDetail.observe(viewLifecycleOwner, Observer {expense ->
            expense ?.let{
                this.findNavController().navigate(
                    ExpenseDetailFragmentDirections.actionExpenseDetailFragmentToHomeFragment()
                )
                viewModel.onDoneNavigatingToDetail()
            }
        })

        return binding.root
    }
}