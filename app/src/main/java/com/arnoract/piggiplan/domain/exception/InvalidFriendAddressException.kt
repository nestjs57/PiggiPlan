package com.arnoract.piggiplan.domain.exception

import com.arnoract.piggiplan.base.BusinessException
import org.apache.commons.lang3.builder.HashCodeBuilder

class InvalidFriendAddressException(
    val isBlankFriendName: Boolean,
    val isBlankAddressName: Boolean
) : BusinessException("Invalid Friend") {

    fun isSomeInvalid(): Boolean {
        return isBlankFriendName || isBlankAddressName
    }

    override fun hashCode(): Int {
        return HashCodeBuilder().append(isBlankFriendName).append(isBlankAddressName).toHashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is InvalidFriendAddressException) return false
        return other.isBlankFriendName == isBlankFriendName && other.isBlankAddressName == isBlankAddressName
    }

}