package com.arnoract.piggiplan.ui.di

import com.arnoract.piggiplan.ui.create.SelectAddressViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val createModule = module {
    viewModel { SelectAddressViewModel() }
}