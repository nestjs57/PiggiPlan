package com.arnoract.piggiplan.domain

import com.arnoract.piggiplan.pref.prefModule
import org.koin.core.context.loadKoinModules

object DomainComponent {
    fun init() = loadKoinModules(listOf(useCaseModule, repositoryModule, prefModule))
}