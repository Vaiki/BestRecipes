package com.example.bestrecipes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bestrecipes.R
import com.example.bestrecipes.databinding.FragmentSavedBinding

class SavedFragment : Fragment(R.layout.fragment_saved) {
    private var viewBinding: FragmentSavedBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentSavedBinding.bind(view)


    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}