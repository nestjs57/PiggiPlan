package com.arnoract.piggiplan.core

import com.arnoract.piggiplan.core.api.branch.BranchApi
import com.arnoract.piggiplan.core.api.restaurant.RestaurantApi
import retrofit2.Retrofit

class ApiBuilder(
    private val retrofit: Retrofit
) {
    fun branchApi(): BranchApi = retrofit.create()
    fun restaurantApi(): RestaurantApi = retrofit.create()
}
