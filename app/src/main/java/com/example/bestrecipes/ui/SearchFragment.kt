package com.example.bestrecipes.ui

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bestrecipes.R
import com.example.bestrecipes.api.models.Hit
import com.example.bestrecipes.api.models.Recipe
import com.example.bestrecipes.databinding.FragmentSearchBinding
import com.example.bestrecipes.ui.adapter.RecipesRecyclerView
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search), OnKeyListener {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RecipeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)
        binding.teSearch.setOnKeyListener(this)
        initRecyclerView()

    }

    private fun initRecyclerView() {
        binding.rvSearchRecipe.layoutManager = LinearLayoutManager(context)
        displayEmployeeList()
    }

    private fun displayEmployeeList() {

        viewModel.getRecipesLiveData.observe(viewLifecycleOwner) {
            val adapter = RecipesRecyclerView(it)
            binding.rvSearchRecipe.adapter = adapter
            adapter.setOnItemClickListener {
                findNavController().navigate(
                    R.id.action_searchFragment_to_articleFragment,
                    bundleOf(ArticleFragment.ARTICLE_KEY to it.url)
                )
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {
            R.id.te_search -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    viewModel.getResponseRecipes(binding.teSearch.text.toString())

                    return true
                }
            }

        }
        return false
    }
}
