package com.arnoract.piggiplan.domain

import org.koin.core.context.loadKoinModules

object DomainComponent {
    fun init() = loadKoinModules(domainModule)
}