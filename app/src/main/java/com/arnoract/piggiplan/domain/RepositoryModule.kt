package com.arnoract.piggiplan.domain

import com.arnoract.piggiplan.domain.branch.BranchRepository
import com.arnoract.piggiplan.domain.branch.BranchRepositoryImpl
import com.arnoract.piggiplan.domain.favorite.FavoriteRepository
import com.arnoract.piggiplan.domain.favorite.FavoriteRepositoryImpl
import com.arnoract.piggiplan.domain.history.HistoryRepository
import com.arnoract.piggiplan.domain.history.HistoryRepositoryImpl
import com.arnoract.piggiplan.domain.restaurant.RestaurantRepository
import com.arnoract.piggiplan.domain.restaurant.RestaurantRepositoryImpl
import com.arnoract.piggiplan.domain.tutorial.TutorialRepository
import com.arnoract.piggiplan.domain.tutorial.TutorialRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<RestaurantRepository> { RestaurantRepositoryImpl(get(), get()) }
    single<BranchRepository> { BranchRepositoryImpl(get(), get()) }
    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
    single<HistoryRepository> { HistoryRepositoryImpl(get(), get()) }
    single<TutorialRepository> { TutorialRepositoryImpl(get()) }
}