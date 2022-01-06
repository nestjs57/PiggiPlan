package com.arnoract.piggiplan.core

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesBuilder(val context: Context) {
    fun buildDefault(): SharedPreferences {
        return context.applicationContext.getSharedPreferences(
            KoinConst.SharedPreference.DEFAULT,
            Context.MODE_PRIVATE,
        )
    }
}
