package com.arnoract.piggiplan.test.utils

fun <T : Any?> successResult(data: T): Result<T> = Result.success(data)

fun <T : Any?> failResult(): Result<T> = Result.failure(RuntimeException("Error"))
fun <T : Any?> failResult(exception: Exception): Result<T> = Result.failure(exception)

