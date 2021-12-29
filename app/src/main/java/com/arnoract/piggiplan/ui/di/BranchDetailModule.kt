package com.arnoract.piggiplan.ui.di

import com.arnoract.piggiplan.core.db.model.branch.BranchId
import com.arnoract.piggiplan.ui.branch.BranchDetailInformationViewModel
import com.arnoract.piggiplan.ui.branch.BranchDetailNearbyViewModel
import com.arnoract.piggiplan.ui.branch.BranchDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val branchDetailModule = module {
    viewModel { (branchId: BranchId) ->
        BranchDetailViewModel(branchId, get(), get())
    }
    viewModel { (branchId: BranchId) ->
        BranchDetailInformationViewModel(branchId)
    }
    viewModel { (branchId: BranchId) ->
        BranchDetailNearbyViewModel(branchId)
    }
}