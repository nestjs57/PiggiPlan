package com.arnoract.piggiplan.test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arnoract.piggiplan.test.rule.CoroutinesTestRule
import io.mockk.mockkStatic
import org.junit.Before
import org.junit.Rule

abstract class BaseViewModelTest {
	@get:Rule
	var instantTaskExecutorRule = InstantTaskExecutorRule()

	@get:Rule
	var coroutinesTestRule = CoroutinesTestRule()

	@Before
	fun setUp() {
		mockkStatic("com.arnoract.piggiplan.test.utils.UseCaseUtilsKt")
		setUpTest()
	}

	protected abstract fun setUpTest()
}
