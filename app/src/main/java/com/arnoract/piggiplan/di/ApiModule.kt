package com.arnoract.piggiplan.di

import com.arnoract.piggiplan.core.ApiBuilder
import org.koin.dsl.module

val apiModule = module {
    single { ApiBuilder(get()).branchApi() }
    single { ApiBuilder(get()).restaurantApi() }
}