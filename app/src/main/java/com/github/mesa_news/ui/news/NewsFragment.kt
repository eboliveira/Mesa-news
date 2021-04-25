package com.github.mesa_news.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mesa_news.R
import com.github.mesa_news.data.models.New
import com.github.mesa_news.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var highlightedNewsAdapter: NewAdapter

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
        highlightedNewsAdapter = NewAdapter()
        binding.root.findViewById<RecyclerView>(R.id.recycler_veiew).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = highlightedNewsAdapter
        }

        return binding.root
    }
}
