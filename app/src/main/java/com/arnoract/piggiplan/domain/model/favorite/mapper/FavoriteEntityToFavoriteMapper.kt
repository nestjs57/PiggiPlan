package com.arnoract.piggiplan.domain.model.favorite.mapper

import com.arnoract.piggiplan.core.Mapper
import com.arnoract.piggiplan.core.db.model.favorite.FavoriteEntity
import com.arnoract.piggiplan.domain.model.favorite.Favorite

object FavoriteEntityToFavoriteMapper : Mapper<FavoriteEntity, Favorite> {
    override fun map(from: FavoriteEntity): Favorite {
        return Favorite(
            id = from.favoriteId,
            branchId = from.branchId
        )
    }
}