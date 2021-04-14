package com.example.farmereazyagric.screens.addexpenses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.farmereazyagric.R
import com.example.farmereazyagric.databinding.FragmentAddExpensesBinding
import com.example.farmereazyagric.databinding.FragmentAddIncomesBinding
import com.example.farmereazyagric.screens.addincomes.AddIncomesViewModel
import com.example.farmereazyagric.screens.addincomes.AddIncomesViewModelFactory

class AddExpensesFragment : Fragment() {

    private lateinit var viewModel: AddExpensesViewModel
    private lateinit var viewModelFactory: AddExpensesViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAddExpensesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_expenses,
            container,
            false)

        val application = requireNotNull(this.activity).application

        viewModelFactory = AddExpensesViewModelFactory(application)

        viewModel =  ViewModelProvider(this).get(AddExpensesViewModel::class.java)

        binding.doneButton.setOnClickListener { view: View -> view.findNavController().navigate(R.id.action_addExpensesFragment_to_expenseFragment)  }

        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.viewModel = viewModel

        return binding.root
    }

}