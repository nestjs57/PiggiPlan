package com.arnoract.piggiplan.ui.summary

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.arnoract.piggiplan.base.recyclerview.ItemViewHolder
import com.arnoract.piggiplan.ui.summary.model.UiSummaryBranch
import com.arnoract.piggiplan.ui.summary.model.UiSummaryBranchDiffCallback

class SummaryBranchAdapter(private val onBranchItemClickListener: SummaryBranchViewHolder.OnBranchItemClickListener) :
    ListAdapter<UiSummaryBranch, ItemViewHolder<UiSummaryBranch>>(
        UiSummaryBranchDiffCallback()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<UiSummaryBranch> {
        return SummaryBranchViewHolder.create(parent, onBranchItemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder<UiSummaryBranch>, position: Int) {
        holder.fillData(getItem(position), position)
    }
}