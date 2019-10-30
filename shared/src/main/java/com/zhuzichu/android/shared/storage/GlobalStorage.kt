package com.zhuzichu.android.shared.storage

import android.content.Context
import android.content.SharedPreferences
import com.zhuzichu.android.shared.global.AppGlobal.context

class GlobalStorage {
    companion object {
        const val PREFS_NAME = "global-preference"
    }

    private val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(
            PREFS_NAME, Context.MODE_PRIVATE
        )
    }

    var token by StringPreference(prefs, null)

}