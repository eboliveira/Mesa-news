package com.github.mesa_news

import android.app.Application
import com.github.mesa_news.data.local.DatabaseInterface
import com.github.mesa_news.data.local.SharedPreferences

class MesaNews: Application() {
    override fun onCreate() {
        super.onCreate()
        DatabaseInterface.startDatabase(this)
        SharedPreferences(this)
        // TODO remove that when authentication is implemented
        SharedPreferences.token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZW1haWwiOiJqb2huQGRvZS5jb20ifQ.08rKeRUP3TiiEUbeIGZGDZRvVmxISOprpEWkWDCpbOo"
    }
}
