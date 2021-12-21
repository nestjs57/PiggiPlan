package com.arnoract.piggiplan.core.db.model.restaurant

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class RestaurantEntity(
    @PrimaryKey
    @ColumnInfo(name = "res_id")
    val id: RestaurantId,
    @ColumnInfo(name = "photo")
    val photo: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "name_en")
    val nameEnglish: String,
    @ColumnInfo(name = "res_type")
    val restaurantType: Int
)

typealias RestaurantId = Long

