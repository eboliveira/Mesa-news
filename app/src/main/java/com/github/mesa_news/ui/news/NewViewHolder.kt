package com.github.mesa_news.ui.news

import android.graphics.BitmapFactory
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.github.mesa_news.R
import com.github.mesa_news.databinding.NewItemBinding
import java.net.URL


class NewViewHolder(val viewDataBinding: NewItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    fun bind() {
        viewDataBinding.let {
            try {
                val imageUrl = URL(it.aNew!!.imageUrl)
                val imageBitmap =
                    BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream())
                it.newImage.setImageBitmap(imageBitmap)
            } catch (e: Exception) {
                // TODO implement error handling or reporting
            }
            
            it.newImage.visibility = View.VISIBLE
            it.imageProgressBar.visibility = View.GONE
        }
    }

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.new_item
    }
}
