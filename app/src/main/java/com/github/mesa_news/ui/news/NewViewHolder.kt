package com.github.mesa_news.ui.news

import android.graphics.BitmapFactory
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.github.mesa_news.R
import com.github.mesa_news.data.local.DatabaseInterface
import com.github.mesa_news.databinding.NewItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL


class NewViewHolder(val viewDataBinding: NewItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    fun bind() {
        viewDataBinding.aNew ?: return

        viewDataBinding.let { view ->
            view.favoriteStar.setOnClickListener {
                val isFavorite = viewDataBinding.aNew!!.favorite
                viewDataBinding.aNew!!.favorite = !isFavorite
                val iconResource =
                    if (isFavorite) R.drawable.ic_empty_star else R.drawable.ic_filled_star
                viewDataBinding.favoriteStar.setImageResource(iconResource)
                CoroutineScope(Dispatchers.IO).launch {
                    DatabaseInterface.getDatabase()?.newDao()?.updateNew(viewDataBinding.aNew!!)
                }
            }

            try {
                val imageUrl = URL(view.aNew!!.imageUrl)
                val imageBitmap =
                    BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream())
                view.newImage.setImageBitmap(imageBitmap)
            } catch (e: Exception) {
                // TODO implement error handling or reporting
            }
            if (view.aNew!!.favorite)
                view.favoriteStar.setImageResource(R.drawable.ic_filled_star)
            view.newImage.visibility = View.VISIBLE
            view.imageProgressBar.visibility = View.GONE
        }
    }

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.new_item
    }
}
