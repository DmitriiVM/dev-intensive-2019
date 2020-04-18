package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
import ru.skillbranch.devintensive.App

object PreferencesRepository {

    private const val FIRST_NAME = "FIRST_NAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val ABOUT = "ABOUT"
    private const val REPOSITORY = "REPOSITORY"
    private const val RATING = "RATING"
    private const val RESPECT = "TV_RESPECT"
    private const val APP_THEME = "APP_THEME"

    private val prefs: SharedPreferences by lazy {
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun saveAppTheme(theme: Int) {
        putValue(APP_THEME to theme)
    }

    fun getAppTheme() = prefs.getInt(APP_THEME, AppCompatDelegate.MODE_NIGHT_NO)



    private fun putValue(pair: Pair<String, Any>) = with(prefs.edit()) {
        val key = pair.first
        val value = pair.second

        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitive types can be stored in Shared Preferences")
        }

        apply()
    }

}