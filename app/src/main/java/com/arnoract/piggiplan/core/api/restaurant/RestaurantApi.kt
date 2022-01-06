package com.arnoract.piggiplan.core.api.restaurant

import com.arnoract.piggiplan.core.api.restaurant.model.RestaurantsResponse
import retrofit2.Response
import retrofit2.http.GET

interface RestaurantApi {
    @GET("restaurants.txt?alt=media&token=f1050ff8-81bf-429e-93f0-deb18a65d8b4")
    suspend fun fetchAllRestaurants(
    ): Response<List<RestaurantsResponse>>
}