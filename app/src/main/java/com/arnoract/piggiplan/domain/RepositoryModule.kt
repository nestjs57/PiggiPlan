package com.arnoract.piggiplan.domain

import com.arnoract.piggiplan.domain.branch.BranchRepository
import com.arnoract.piggiplan.domain.branch.BranchRepositoryImpl
import com.arnoract.piggiplan.domain.restaurant.RestaurantRepository
import com.arnoract.piggiplan.domain.restaurant.RestaurantRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<RestaurantRepository> { RestaurantRepositoryImpl(get()) }
    single<BranchRepository> { BranchRepositoryImpl(get()) }
}