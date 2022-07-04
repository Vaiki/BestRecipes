package com.example.bestrecipes.ui

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import com.example.bestrecipes.R
import com.example.bestrecipes.api.models.Hit
import com.example.bestrecipes.api.models.Recipe
import com.example.bestrecipes.databinding.FragmentSearchBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment(R.layout.fragment_search), OnKeyListener {
    private var viewBinding: FragmentSearchBinding? = null

    private val viewModel: RecipeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentSearchBinding.bind(view)
        viewBinding?.teSearch?.setOnKeyListener(this)
        viewModel.getRecipesLiveData.observe(viewLifecycleOwner){
            Log.e("Response",it.hits[0].recipe.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun onKey(view: View, i: Int, keyEvent: KeyEvent): Boolean {
        when (view.id) {
            R.id.te_search -> {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {

                    viewModel.getResponseRecipes(viewBinding?.teSearch?.text.toString())

                    return true
                }
            }

        }
        return false
    }
}
