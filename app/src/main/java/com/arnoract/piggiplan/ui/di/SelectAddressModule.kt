package com.arnoract.piggiplan.ui.di

import com.arnoract.piggiplan.ui.create.SelectAddressViewModel
import com.arnoract.piggiplan.ui.create.SelectAddressViewModelDelegateImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val selectAddressModule = module {
    viewModel { SelectAddressViewModel(get()) }
    single { SelectAddressViewModelDelegateImpl() }
}