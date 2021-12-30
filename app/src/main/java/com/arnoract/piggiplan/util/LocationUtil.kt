package com.arnoract.piggiplan.util

import android.location.Location

fun getDistanceMeter(locationA: Location, locationB: Location): Float {
    return locationA.distanceTo(locationB)
}