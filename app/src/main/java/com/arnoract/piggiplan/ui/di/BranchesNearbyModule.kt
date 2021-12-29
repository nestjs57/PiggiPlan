package com.arnoract.piggiplan.ui.di

import com.arnoract.piggiplan.ui.branch.BranchesNearbyDelegateImpl
import com.arnoract.piggiplan.ui.branch.BranchesNearbyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val branchesNearbyModule = module {
    viewModel {
        BranchesNearbyViewModel(get())
    }
    single { BranchesNearbyDelegateImpl() }
}