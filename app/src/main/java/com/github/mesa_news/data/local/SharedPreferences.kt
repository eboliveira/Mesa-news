package com.github.mesa_news.data.local

import android.content.Context

class SharedPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: SharedPreferences
        private const val APP_SETTINGS = "app_settings"

        private const val TOKEN = "token"

        var token: String?
            get() = instance.sharedPreferences.getString(TOKEN, null)
            set(value) {
                instance.editor.putString(TOKEN, value).apply()
            }
    }
}