package com.github.mesa_news.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.mesa_news.data.models.New
import com.github.mesa_news.databinding.NewItemBinding

class NewAdapter : RecyclerView.Adapter<NewViewHolder>() {
    var news = emptyList<New>()
        set(value) {
            field = value

            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val withDataBinding: NewItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            NewViewHolder.LAYOUT,
            parent,
            false
        )
        return NewViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.aNew = news[position]
            holder.bind()
        }
    }

    override fun getItemCount(): Int = news.size
}
