package com.arnoract.piggiplan.di

import com.arnoract.piggiplan.core.sharedPreferencesModule
import org.koin.core.context.loadKoinModules

object AppComponent {
    fun init() = loadKoinModules(
        listOf(
            coreModule,
            apiModule,
            databaseModule,
            apiModule,
            sharedPreferencesModule
        )
    )
}