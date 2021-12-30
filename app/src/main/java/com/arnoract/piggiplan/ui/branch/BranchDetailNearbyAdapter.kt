package com.arnoract.piggiplan.ui.branch

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.arnoract.piggiplan.base.recyclerview.ItemViewHolder
import com.arnoract.piggiplan.ui.branch.model.UiBranchDetailNearby
import com.arnoract.piggiplan.ui.branch.model.UiBranchDetailNearbyDiffCallback

class BranchDetailNearbyAdapter(private val onBranchItemClickListener: BranchDetailNearbyViewHolder.OnBranchItemClickListener) :
    ListAdapter<UiBranchDetailNearby, ItemViewHolder<UiBranchDetailNearby>>(
        UiBranchDetailNearbyDiffCallback()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<UiBranchDetailNearby> {
        return BranchDetailNearbyViewHolder.create(parent, onBranchItemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder<UiBranchDetailNearby>, position: Int) {
        holder.fillData(getItem(position), position)
    }
}