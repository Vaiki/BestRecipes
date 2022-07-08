package com.example.bestrecipes.ui

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.bestrecipes.R
import com.example.bestrecipes.databinding.FragmentArticleBinding


class ArticleFragment : Fragment(R.layout.fragment_article) {

    private var viewBinding: FragmentArticleBinding? = null
    private val binding get() = viewBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentArticleBinding.bind(view)
       val url = requireArguments().getString(ARTICLE_KEY)
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(url!!)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
    companion object{
        const val ARTICLE_KEY = "article"
    }
}
