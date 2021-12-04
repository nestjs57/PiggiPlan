package com.arnoract.piggiplan.di

import com.arnoract.piggiplan.common.time.DefaultTimeProvider
import com.arnoract.piggiplan.common.time.TimeProvider
import com.arnoract.piggiplan.core.CoroutinesDispatcherProvider
import com.arnoract.piggiplan.core.MyGsonBuilder
import com.arnoract.piggiplan.core.OkHttpBuilder
import com.arnoract.piggiplan.core.RetrofitBuilder
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val coreModule = module {
    single { MyGsonBuilder().build() }
    single { OkHttpBuilder(androidApplication()).build() }
    single { RetrofitBuilder(get(), get()).build() }
    single { CoroutinesDispatcherProvider() }
    single<TimeProvider> { DefaultTimeProvider() }
}