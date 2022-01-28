package com.arnoract.piggiplan.domain.tutorial

import com.arnoract.piggiplan.core.UseCase

class GetIsShowSearchBranchNearbyUseCase(
    private val tutorialRepository: TutorialRepository
) : UseCase<Unit, Boolean>() {
    override suspend fun execute(parameters: Unit): Boolean {
        return tutorialRepository.getIsShowSearchBranchNearby()
    }
}