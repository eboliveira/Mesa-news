package com.github.mesa_news.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mesa_news.R
import com.github.mesa_news.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentNewsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_news,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        binding.viewModel = newsViewModel
        return binding.root
    }
}
