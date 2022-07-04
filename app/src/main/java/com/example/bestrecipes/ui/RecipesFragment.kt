package com.example.bestrecipes.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bestrecipes.R
import com.example.bestrecipes.databinding.FragmentRecipeBinding

class RecipesFragment : Fragment(R.layout.fragment_recipe) {
    private var viewBinding: FragmentRecipeBinding? = null
    private val viewModel: RecipeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentRecipeBinding.bind(view)



    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}