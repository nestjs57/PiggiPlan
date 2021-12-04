package com.arnoract.piggiplan.common.time

import com.arnoract.piggiplan.common.time.TimeProvider
import java.util.*

class DefaultTimeProvider : TimeProvider {
    override fun now(): Long {
        return Date().time
    }

    override fun getCurrentTimeZone(): TimeZone {
        return TimeZone.getDefault()
    }
}
