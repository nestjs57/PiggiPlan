package com.arnoract.piggiplan.ui.di

import com.arnoract.piggiplan.ui.create.SearchBranchesNearbyViewModel
import com.arnoract.piggiplan.ui.create.SearchBranchesNearbyViewModelDelegateImpl
import com.arnoract.piggiplan.ui.create.SelectAddressViewModel
import com.arnoract.piggiplan.ui.create.SelectAddressViewModelDelegateImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchBranchNearby = module {
    viewModel { (historyId: String) ->
        SearchBranchesNearbyViewModel(
            historyId,
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel { SelectAddressViewModel(get()) }
    single { SearchBranchesNearbyViewModelDelegateImpl() }
    single { SelectAddressViewModelDelegateImpl() }
}