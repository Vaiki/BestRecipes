package com.example.bestrecipes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bestrecipes.R
import com.example.bestrecipes.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var viewBinding: FragmentSearchBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentSearchBinding.bind(view)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}