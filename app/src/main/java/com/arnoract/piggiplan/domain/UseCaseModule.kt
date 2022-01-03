package com.arnoract.piggiplan.domain

import com.arnoract.piggiplan.domain.branch.GetBranchByBranchIdUseCase
import com.arnoract.piggiplan.domain.branch.GetBranchesByRestaurantIdUseCase
import com.arnoract.piggiplan.domain.branch.GetBranchesUseCase
import com.arnoract.piggiplan.domain.create.ValidateFriendAddressUseCase
import com.arnoract.piggiplan.domain.favorite.DeleteAllFavoritesUseCase
import com.arnoract.piggiplan.domain.favorite.GetIsFavoriteUseCase
import com.arnoract.piggiplan.domain.favorite.ObserveFavoritesUseCase
import com.arnoract.piggiplan.domain.favorite.UpdateFavoriteUseCase
import com.arnoract.piggiplan.domain.history.GetSearchBranchNearbyHistoryUseCase
import com.arnoract.piggiplan.domain.history.SaveSearchBranchNearbyHistoryUseCase
import com.arnoract.piggiplan.domain.launchscreen.InitialFetchUseCase
import com.arnoract.piggiplan.domain.restaurant.GetRestaurantByIdUseCase
import com.arnoract.piggiplan.domain.restaurant.GetRestaurantsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { ValidateFriendAddressUseCase() }
    factory { InitialFetchUseCase(get(), get()) }
    factory { GetRestaurantsUseCase(get()) }
    factory { GetBranchesByRestaurantIdUseCase(get()) }
    factory { GetBranchByBranchIdUseCase(get()) }
    factory { UpdateFavoriteUseCase(get()) }
    factory { ObserveFavoritesUseCase(get()) }
    factory { GetIsFavoriteUseCase(get()) }
    factory { GetBranchesUseCase(get()) }
    factory { DeleteAllFavoritesUseCase(get()) }
    factory { SaveSearchBranchNearbyHistoryUseCase(get()) }
    factory { GetSearchBranchNearbyHistoryUseCase(get()) }
    factory { GetRestaurantByIdUseCase(get()) }
}