package com.arnoract.piggiplan.ui.summary.model

import androidx.recyclerview.widget.DiffUtil
import com.arnoract.piggiplan.core.db.model.branch.BranchId

data class UiSummaryBranch(
    val branchId: BranchId,
    val photoImage: String,
    val branchName: String,
    val description: String
)

class UiSummaryBranchDiffCallback : DiffUtil.ItemCallback<UiSummaryBranch>() {
    override fun areItemsTheSame(
        oldItem: UiSummaryBranch,
        newItem: UiSummaryBranch,
    ): Boolean {
        return oldItem.branchId == newItem.branchId
    }

    override fun areContentsTheSame(
        oldItem: UiSummaryBranch,
        newItem: UiSummaryBranch,
    ): Boolean {
        return oldItem == newItem
    }
}
