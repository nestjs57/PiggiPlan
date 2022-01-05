package com.arnoract.piggiplan.domain.tutorial

import com.arnoract.piggiplan.pref.tutorial.TutorialPreferenceStorage

class TutorialRepositoryImpl(
    private val tutorialPreferenceStorage: TutorialPreferenceStorage
) : TutorialRepository {
    override suspend fun getIsShowSearchBranchNearby(): Boolean {
        val isShow = tutorialPreferenceStorage.isShowSearchBranchNearby
        if (isShow) tutorialPreferenceStorage.isShowSearchBranchNearby = false
        return isShow
    }
}