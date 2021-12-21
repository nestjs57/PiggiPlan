package com.arnoract.piggiplan.domain

import com.arnoract.piggiplan.domain.create.ValidateFriendAddressUseCase
import com.arnoract.piggiplan.domain.launchscreen.InitialFetchUseCase
import com.arnoract.piggiplan.domain.restaurant.GetRestaurantsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { ValidateFriendAddressUseCase() }
    factory { InitialFetchUseCase(get(), get()) }
    factory { GetRestaurantsUseCase(get()) }
}