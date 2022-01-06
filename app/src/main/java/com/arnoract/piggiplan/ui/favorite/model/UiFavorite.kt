package com.arnoract.piggiplan.ui.favorite.model

import androidx.recyclerview.widget.DiffUtil
import com.arnoract.piggiplan.core.db.model.branch.BranchId

data class UiFavorite(
    val id: BranchId,
    val photoImage: String,
    val branchName: String,
    val description: String,
    val isFavorite: Boolean
)

class UiFavoriteDiffCallback : DiffUtil.ItemCallback<UiFavorite>() {
    override fun areItemsTheSame(
        oldItem: UiFavorite,
        newItem: UiFavorite,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: UiFavorite,
        newItem: UiFavorite,
    ): Boolean {
        return oldItem == newItem
    }
}
