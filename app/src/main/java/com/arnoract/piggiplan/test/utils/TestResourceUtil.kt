package com.arnoract.piggiplan.test.utils

object TestResourceUtil {
	fun readTestResource(fileName: String): String {
		return javaClass.classLoader?.getResourceAsStream(fileName)
			?.bufferedReader()
			?.readText() ?: ""
	}
}
