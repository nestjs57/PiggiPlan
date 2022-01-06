package com.arnoract.piggiplan.domain.tutorial

interface TutorialRepository {
    suspend fun getIsShowSearchBranchNearby(): Boolean
}