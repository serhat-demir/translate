package com.serhatd.translate.data.prefs

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(private val context: Context) {
    private val COL_SKIP_INTRO = "skip_intro"

    fun setIntroSkipped() {
        val sharedPref: SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        val edit = sharedPref.edit()

        edit.putBoolean(COL_SKIP_INTRO, true)
        edit.apply()
    }

    fun isIntroSkipped(): Boolean {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE).getBoolean(COL_SKIP_INTRO, false)
    }
}