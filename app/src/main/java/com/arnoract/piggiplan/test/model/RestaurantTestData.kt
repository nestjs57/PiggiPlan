package com.arnoract.piggiplan.test.model

import com.arnoract.piggiplan.domain.model.restaurant.Restaurant

object RestaurantTestData {
    private val res1 = Restaurant(
        1L,
        "photo", "name", "nameEn", Restaurant.Type.FOOD
    )
    private val res2 = Restaurant(
        2L,
        "photo", "name", "nameEn", Restaurant.Type.ICE_CREAM
    )
    private val res3 = Restaurant(
        3L,
        "photo", "name", "nameEn", Restaurant.Type.CAFE
    )
    private val res4 = Restaurant(
        4L,
        "photo", "name", "nameEn", Restaurant.Type.SHABU
    )
    private val res5 = Restaurant(
        5L,
        "photo", "name", "nameEn", Restaurant.Type.SHABU
    )
    private val res6 = Restaurant(
        6L,
        "photo", "name", "nameEn", Restaurant.Type.THAI_BBQ
    )
    private val res7 = Restaurant(
        7L,
        "photo", "name", "nameEn", Restaurant.Type.FOOD
    )
    private val res8 = Restaurant(
        8L,
        "photo", "name", "nameEn", Restaurant.Type.THAI_BBQ
    )
    private val res9 = Restaurant(
        9L,
        "photo", "name", "nameEn", Restaurant.Type.CAFE
    )
    private val res10 = Restaurant(
        10L,
        "photo", "name", "nameEn", Restaurant.Type.CAFE
    )

    fun all() = listOf(res1, res2, res3, res4, res5, res6, res7, res8, res9, res10)
}