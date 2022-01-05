package com.arnoract.piggiplan.pref.tutorial

import android.content.SharedPreferences
import androidx.core.content.edit

interface TutorialPreferenceStorage {
    var isShowSearchBranchNearby: Boolean
}

class SharedPreferencesTutorialPreferenceStorage(
    private val sharedPreferences: SharedPreferences
) : TutorialPreferenceStorage {

    companion object {
        private const val KEY_SHOW_SEARCH_BRANCH_NEARBY = "show-search-branch-nearby"
    }

    override var isShowSearchBranchNearby: Boolean
        get() = sharedPreferences.getBoolean(KEY_SHOW_SEARCH_BRANCH_NEARBY, false)
        set(value) {
            sharedPreferences.edit(commit = true) {
                putBoolean(KEY_SHOW_SEARCH_BRANCH_NEARBY, value)
            }
        }
}