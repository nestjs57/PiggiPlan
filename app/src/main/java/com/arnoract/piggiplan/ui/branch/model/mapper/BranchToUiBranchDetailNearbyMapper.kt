package com.arnoract.piggiplan.ui.branch.model.mapper

import android.location.Location
import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.domain.model.branch.Branch
import com.arnoract.piggiplan.ui.branch.model.UiBranchDetailNearby
import com.arnoract.piggiplan.util.getDistanceMeter

object BranchToUiBranchDetailNearbyMapper :
    Mapper<BranchToUiBranchDetailNearbyMapper.Params, UiBranchDetailNearby> {

    override fun map(from: Params): UiBranchDetailNearby {
        return UiBranchDetailNearby(
            id = from.branch.branchId,
            photoImage = from.branch.photoImage,
            branchName = from.branch.branchName,
            description = from.branch.description,
            distance = getDistanceMeter(
                getLocation(from.currentBranch.latitude, from.currentBranch.longitude),
                getLocation(from.branch.latitude, from.branch.longitude)
            )
        )
    }

    private fun getLocation(lat: Double, long: Double): Location {
        return Location("location").apply {
            latitude = lat
            longitude = long
        }
    }

    data class Params(
        val currentBranch: Branch,
        val branch: Branch
    )
}