package com.arnoract.piggiplan.ui.di

import com.arnoract.piggiplan.ui.create.EditFriendViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val editFriendModule = module {
    viewModel { (id: Long) ->
        EditFriendViewModel(id, get(), get(), get(), get())
    }
}