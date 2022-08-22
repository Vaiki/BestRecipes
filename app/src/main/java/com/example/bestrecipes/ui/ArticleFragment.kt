package com.example.bestrecipes.ui

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bestrecipes.R
import com.example.bestrecipes.databinding.FragmentArticleBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ArticleFragment : Fragment(R.layout.fragment_article) {

    private var viewBinding: FragmentArticleBinding? = null
    private val binding get() = viewBinding!!
    private val viewModel by sharedViewModel<RecipeViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentArticleBinding.bind(view)
        val url = requireArguments().getString(ARTICLE_KEY)

        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(url!!)
        }
        binding.fab.setOnClickListener {
            viewModel.recipeForSaveLiveData.observe(viewLifecycleOwner) {
                viewModel.saveRecipe(it)
            }
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    companion object {
        const val ARTICLE_KEY = "article"
    }
}
