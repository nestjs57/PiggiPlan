package com.arnoract.piggiplan.ui.di

import com.arnoract.piggiplan.core.db.model.restaurant.RestaurantId
import com.arnoract.piggiplan.ui.restaurant.SelectRestaurantViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val restaurantModule = module {
    viewModel { (id: RestaurantId) ->
        SelectRestaurantViewModel(id, get(), get())
    }
}
