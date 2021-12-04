package com.arnoract.piggiplan.di

import org.koin.core.context.loadKoinModules

object AppComponent {
    fun init() = loadKoinModules(listOf(coreModule, apiModule))
}