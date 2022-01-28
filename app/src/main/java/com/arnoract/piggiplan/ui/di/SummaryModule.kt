package com.arnoract.piggiplan.ui.di

import com.arnoract.piggiplan.ui.summary.SummaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val summaryModule = module {
    viewModel { SummaryViewModel(get(), get()) }
}