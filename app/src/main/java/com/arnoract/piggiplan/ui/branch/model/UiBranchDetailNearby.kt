package com.arnoract.piggiplan.ui.branch.model

import androidx.recyclerview.widget.DiffUtil
import com.arnoract.piggiplan.core.db.model.branch.BranchId

data class UiBranchDetailNearby(
    val id: BranchId,
    val photoImage: String,
    val branchName: String,
    val description: String,
    val distance: Float
)

class UiBranchDetailNearbyDiffCallback : DiffUtil.ItemCallback<UiBranchDetailNearby>() {
    override fun areItemsTheSame(
        oldItem: UiBranchDetailNearby,
        newItem: UiBranchDetailNearby,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UiBranchDetailNearby,
        newItem: UiBranchDetailNearby,
    ): Boolean {
        return oldItem == newItem
    }
}