package com.arnoract.piggiplan.ui.branch.model

import androidx.recyclerview.widget.DiffUtil
import com.arnoract.piggiplan.core.db.model.branch.BranchId

data class UiBranchNearby(
    val id: BranchId,
    val photoImage: String,
    val branchName: String,
    val description: String,
    val isFavorite: Boolean
)

class UiBranchNearbyDiffCallback : DiffUtil.ItemCallback<UiBranchNearby>() {
    override fun areItemsTheSame(
        oldItem: UiBranchNearby,
        newItem: UiBranchNearby,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UiBranchNearby,
        newItem: UiBranchNearby,
    ): Boolean {
        return oldItem == newItem
    }
}