package com.arnoract.piggiplan.domain.create

import com.arnoract.piggiplan.core.UseCase
import com.arnoract.piggiplan.domain.exception.InvalidFriendAddressException

class ValidateFriendAddressUseCase :
    UseCase<ValidateFriendAddressUseCase.Params, Unit>() {

    override suspend fun execute(parameters: Params) {
        val validateResult = InvalidFriendAddressException(
            parameters.name.isBlank(),
            parameters.addressName.isBlank()
        )
        if (validateResult.isSomeInvalid()) throw validateResult
    }

    data class Params(
        val name: String,
        val addressName: String
    )
}