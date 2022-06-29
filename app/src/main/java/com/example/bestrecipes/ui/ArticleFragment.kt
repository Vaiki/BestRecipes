package com.example.bestrecipes.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bestrecipes.R
import com.example.bestrecipes.databinding.FragmentArticleBinding


class ArticleFragment : Fragment(R.layout.fragment_article) {

    private var viewBinding: FragmentArticleBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentArticleBinding.bind(view)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}
