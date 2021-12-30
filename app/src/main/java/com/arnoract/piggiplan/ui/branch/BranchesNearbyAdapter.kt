package com.arnoract.piggiplan.ui.branch

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.arnoract.piggiplan.base.recyclerview.ItemViewHolder
import com.arnoract.piggiplan.ui.branch.model.UiBranchNearby
import com.arnoract.piggiplan.ui.branch.model.UiBranchNearbyDiffCallback

class BranchesNearbyAdapter(private val onBranchItemClickListener: BranchesNearbyViewHolder.OnBranchItemClickListener) :
    ListAdapter<UiBranchNearby, ItemViewHolder<UiBranchNearby>>(
        UiBranchNearbyDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<UiBranchNearby> {
        return BranchesNearbyViewHolder.create(parent, onBranchItemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder<UiBranchNearby>, position: Int) {
        holder.fillData(getItem(position), position)
    }
}