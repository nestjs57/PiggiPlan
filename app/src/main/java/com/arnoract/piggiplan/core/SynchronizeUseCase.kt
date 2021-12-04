package com.arnoract.piggiplan.core

import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber

/**
 * Executes business logic asynchronously using a [CoroutineDispatcher].
 */
abstract class SynchronizeUseCase<in P, R> {
    operator fun invoke(parameters: P): Result<R> {
        return try {
            Result.Success(execute(parameters))
        } catch (e: Exception) {
            Timber.e(e)
            Result.Failure(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract fun execute(parameters: P): R
}
