package com.arnoract.piggiplan.base

open class BusinessException : RuntimeException {
    constructor() : super()
    constructor(cause: String) : super(cause)
}