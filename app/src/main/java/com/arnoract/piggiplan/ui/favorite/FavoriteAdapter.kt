package com.arnoract.piggiplan.ui.favorite

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.arnoract.piggiplan.base.recyclerview.ItemViewHolder
import com.arnoract.piggiplan.ui.favorite.model.UiFavorite
import com.arnoract.piggiplan.ui.favorite.model.UiFavoriteDiffCallback

class FavoriteAdapter(private val onBranchItemClickListener: FavoriteViewHolder.OnBranchItemClickListener) :
    ListAdapter<UiFavorite, ItemViewHolder<UiFavorite>>(
        UiFavoriteDiffCallback()
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<UiFavorite> {
        return FavoriteViewHolder.create(parent, onBranchItemClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder<UiFavorite>, position: Int) {
        holder.fillData(getItem(position), position)
    }
}