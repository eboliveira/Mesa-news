package com.github.mesa_news.ui.news

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.github.mesa_news.R
import com.github.mesa_news.databinding.NewItemBinding


class NewViewHolder(val viewDataBinding: NewItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.new_item
    }
}
