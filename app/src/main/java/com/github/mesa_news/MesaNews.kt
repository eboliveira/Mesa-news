package com.github.mesa_news

import android.app.Application
import com.github.mesa_news.data.local.DatabaseInterface

class MesaNews: Application() {
    override fun onCreate() {
        super.onCreate()
        DatabaseInterface.startDatabase(this)
    }
}