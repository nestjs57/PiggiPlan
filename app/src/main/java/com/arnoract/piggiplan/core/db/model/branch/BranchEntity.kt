package com.arnoract.piggiplan.core.db.model.branch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId

@Entity(tableName = "branch")
data class BranchEntity(
    @PrimaryKey
    @ColumnInfo(name = "branch_id")
    val branchId: BranchId,
    @ColumnInfo(name = "res_id")
    val resId: RestaurantId,
    @ColumnInfo(name = "photo")
    val photo: String,
    @ColumnInfo(name = "branch_name")
    val branchName: String,
    @ColumnInfo(name = "address_name")
    val addressName: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "lat")
    val latitude: Double,
    @ColumnInfo(name = "lng")
    val longitude: Double,
    @ColumnInfo(name = "tel")
    val tel: String,
)

typealias BranchId = Long

