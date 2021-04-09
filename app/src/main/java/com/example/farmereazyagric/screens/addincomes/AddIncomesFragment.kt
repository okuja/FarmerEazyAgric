package com.example.farmereazyagric.screens.addincomes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.farmereazyagric.R
import com.example.farmereazyagric.databinding.FragmentAddIncomesBinding
import com.example.farmereazyagric.screens.incomes.IncomeViewModel
import com.example.farmereazyagric.screens.incomes.IncomeViewModelFactory


class AddIncomesFragment : Fragment() {
    private lateinit var viewModel: AddIncomesViewModel
    private lateinit var viewModelFactory: AddIncomesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentAddIncomesBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_add_incomes,
                container,
                false)

        val application = requireNotNull(this.activity).application

        viewModelFactory = AddIncomesViewModelFactory(application)

        viewModel =  ViewModelProvider(this).get(AddIncomesViewModel::class.java)

        binding.doneButton.setOnClickListener { view: View -> view.findNavController().navigate(R.id.action_addIncomesFragment_to_incomeFragment)  }

        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.viewModel = viewModel

        return binding.root
    }
}