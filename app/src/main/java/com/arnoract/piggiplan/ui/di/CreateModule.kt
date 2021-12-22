package com.arnoract.piggiplan.ui.di

import com.arnoract.piggiplan.ui.create.*
import com.arnoract.piggiplan.ui.restaurant.SelectRestaurantViewModelDelegateImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createModule = module {
    viewModel { CreateFriendViewModel(get(), get()) }
    viewModel { (id: Long) ->
        EditFriendViewModel(id, get(), get(), get(), get())
    }
    viewModel { SelectAddressViewModel(get()) }
    single { CreatePartyViewModelDelegateImpl() }
    single { SelectAddressViewModelDelegateImpl() }
    single { SelectRestaurantViewModelDelegateImpl() }
}