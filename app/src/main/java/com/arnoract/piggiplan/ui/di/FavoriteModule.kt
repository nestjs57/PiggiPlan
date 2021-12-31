package com.arnoract.piggiplan.ui.di

import com.arnoract.piggiplan.ui.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get(), get(), get(), get(), get()) }
}