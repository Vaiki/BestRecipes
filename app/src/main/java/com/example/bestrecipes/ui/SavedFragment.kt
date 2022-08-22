package com.example.bestrecipes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bestrecipes.R
import com.example.bestrecipes.databinding.FragmentSavedBinding
import com.example.bestrecipes.databinding.FragmentSearchBinding
import com.example.bestrecipes.ui.adapter.SavedRecipesRV
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SavedFragment : Fragment(R.layout.fragment_saved) {
    private var _binding:FragmentSavedBinding? = null
    private val binding get() = _binding!!
    private val viewModel by sharedViewModel<RecipeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSavedBinding.bind(view)
        initRV()

    }

    fun initRV() {
        binding.rvSavedRecipe.layoutManager = LinearLayoutManager(context)
        viewModel.getSavedRecipe().observe(viewLifecycleOwner) {
            binding.rvSavedRecipe.adapter = SavedRecipesRV(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}