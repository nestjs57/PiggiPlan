package com.arnoract.piggiplan.test

import com.arnoract.piggiplan.test.rule.CoroutinesTestRule
import org.junit.Before
import org.junit.Rule

abstract class BaseUseCaseTest {
	@get:Rule
	var coroutinesTestRule = CoroutinesTestRule()

	@Before
	fun setUp() {
		setUpTest()
	}

	protected abstract fun setUpTest()
}
