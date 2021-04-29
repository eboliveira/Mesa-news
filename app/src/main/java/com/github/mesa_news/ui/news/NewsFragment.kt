package com.github.mesa_news.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mesa_news.R
import com.github.mesa_news.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var highlightedNewsAdapter: NewAdapter
    private lateinit var newsAdapter: NewAdapter

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
        binding.root.findViewById<RecyclerView>(R.id.recycler_view_highlighted_news).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = highlightedNewsAdapter
        }

        newsAdapter = NewAdapter()
        binding.root.findViewById<RecyclerView>(R.id.recycler_view_news).apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = newsAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.highlightedNews?.observe(viewLifecycleOwner, { news ->
            news?.apply { highlightedNewsAdapter.news = news }
        })

        newsViewModel.news?.observe(viewLifecycleOwner, { news ->
            news?.apply { newsAdapter.news = news }
        })
    }
}
