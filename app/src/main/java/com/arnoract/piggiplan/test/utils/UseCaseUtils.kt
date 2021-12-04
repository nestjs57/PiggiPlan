package com.arnoract.piggiplan.test.utils

import com.arnoract.piggiplan.core.SynchronizeUseCase
import com.arnoract.piggiplan.core.UseCase
import io.mockk.coEvery
import com.arnoract.piggiplan.core.Result

@Suppress("NOTHING_TO_INLINE")
inline fun <reified P : Any, R : Any?> UseCase<P, R>.mockReturnResult(crossinline answer: (P?) -> Result<R>) {
	coEvery { this@mockReturnResult(any()) } coAnswers {
		val param: P? = firstArg()
		answer(param)
	}
}

@Suppress("NOTHING_TO_INLINE")
inline fun <reified P : Any, R : Any?> SynchronizeUseCase<P, R>.mockReturnResult(crossinline answer: (P?) -> Result<R>) {
	coEvery { this@mockReturnResult(any()) } coAnswers {
		val param: P? = firstArg()
		answer(param)
	}
}
