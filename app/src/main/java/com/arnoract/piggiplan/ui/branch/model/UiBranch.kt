package com.arnoract.piggiplan.ui.branch.model

import androidx.recyclerview.widget.DiffUtil
import com.arnoract.piggiplan.core.db.model.branch.BranchId

data class UiBranch(
    val id: BranchId,
    val photoImage: String,
    val branchName: String,
    val description: String,
)

class UiBranchDiffCallback : DiffUtil.ItemCallback<UiBranch>() {
    override fun areItemsTheSame(
        oldItem: UiBranch,
        newItem: UiBranch,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UiBranch,
        newItem: UiBranch,
    ): Boolean {
        return oldItem == newItem
    }
}
