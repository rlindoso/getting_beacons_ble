package com.rlindoso.gettingbeacons.service.repository.local

import android.content.Context
import android.content.SharedPreferences

/**
 * Acesso a dados rápidos do projeto - SharedPreferences
 */
class SecurityPreferences(context: Context) {

    private val mPreferences: SharedPreferences =
        context.getSharedPreferences("taskShared", Context.MODE_PRIVATE)

    fun store(key: String, value: String) {
        mPreferences.edit().putString(key, value).apply()
    }

    fun get(key: String): String {
        return mPreferences.getString(key, "") ?: ""
    }

}
