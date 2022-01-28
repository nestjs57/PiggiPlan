package com.arnoract.piggiplan.ui.branch

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.arnoract.piggiplan.base.recyclerview.ItemViewHolder
import com.arnoract.piggiplan.ui.branch.model.UiBranch
import com.arnoract.piggiplan.ui.branch.model.UiBranchDiffCallback

class BranchAdapter(private val onBranchItemClickListener: BranchViewHolder.OnBranchItemClickListener) :
    ListAdapter<UiBranch, ItemViewHolder<UiBranch>>(
        UiBranchDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<UiBranch> {
        return BranchViewHolder.create(parent, onBranchItemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder<UiBranch>, position: Int) {
        holder.fillData(getItem(position), position)
    }
}