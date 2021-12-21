package com.arnoract.piggiplan.domain

import com.arnoract.piggiplan.domain.create.ValidateFriendAddressUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { ValidateFriendAddressUseCase() }
}