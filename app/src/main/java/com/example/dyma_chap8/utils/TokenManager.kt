package com.example.dyma_chap8.utils

import android.content.Context
import android.content.SharedPreferences

class TokenManager(val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("TODO_PREFERENCES", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        prefs.edit().putString("auth_token", token).apply()
    }

    fun getToken(): String? {
        return prefs.getString("auth_token", null)
    }

    fun clearToken() {
        prefs.edit().remove("auth_token").apply()
    }

}