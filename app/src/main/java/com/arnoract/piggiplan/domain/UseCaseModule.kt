package com.arnoract.piggiplan.domain

import com.arnoract.piggiplan.domain.create.ValidateFriendAddressUseCase
import com.arnoract.piggiplan.domain.launchscreen.InitialFetchUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { ValidateFriendAddressUseCase() }
    factory { InitialFetchUseCase(get(), get()) }
}