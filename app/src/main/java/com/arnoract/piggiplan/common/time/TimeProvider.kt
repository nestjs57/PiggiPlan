package com.arnoract.piggiplan.common.time

import java.util.*

interface TimeProvider {
    fun now(): Long
    fun getCurrentTimeZone(): TimeZone
}
