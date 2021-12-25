package com.arnoract.piggiplan.util

import android.location.Location

object LocationUtil {
    fun getDistanceMeter(locationA: Location, locationB: Location): Float {
        return locationA.distanceTo(locationB)
    }
}