package com.github.mesa_news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mesa_news.data.repositories.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch {
            NewsRepository.refresh()
        }
    }
}
