package com.arnoract.piggiplan.ui.di

import com.arnoract.piggiplan.ui.login.LaunchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel { LaunchScreenViewModel() }
}