package com.arnoract.piggiplan.ui.di

import org.koin.core.context.loadKoinModules

object UiComponent {
    fun init() = loadKoinModules(listOf(loginModule, homeModule))
}