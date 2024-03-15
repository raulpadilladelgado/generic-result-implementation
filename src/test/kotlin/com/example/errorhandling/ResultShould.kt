package com.example.errorhandling

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ResultShould {
    @Test
    fun `be success`() {
        val result = Result.success("foo")

        Assertions.assertTrue(result.isSuccess)
        Assertions.assertFalse(result.isFailure)
    }

    @Test
    fun `be failure`() {
        val result = Result.failure<String>(Error.Unknown)

        Assertions.assertFalse(result.isSuccess)
        Assertions.assertTrue(result.isFailure)
    }

    @Test
    fun `map result`() {
        val result = Result.success(10)

        val data = result.map { it * 2 }.data
        val error = result.mapError { Error.Foo }.error

        Assertions.assertEquals(20, data)
        Assertions.assertNull(error)

    }

    @Test
    fun `fold result`() {
        val successResult = Result.success(10)
        val errorResult = Result.failure<String>(Error.Foo)

        val successResultFolded = successResult.fold({ it * 2 }, {})
        val errorResultFolded = errorResult.fold({}, { 0 })

        Assertions.assertEquals(20, successResultFolded)
        Assertions.assertEquals(0, errorResultFolded)
    }
}