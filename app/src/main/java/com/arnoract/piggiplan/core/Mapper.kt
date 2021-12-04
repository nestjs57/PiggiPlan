package com.arnoract.piggiplan.core

interface Mapper<in From, out To> {
    fun map(from: From): To
}