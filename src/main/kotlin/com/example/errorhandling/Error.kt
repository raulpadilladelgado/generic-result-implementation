package com.example.errorhandling

sealed interface Error {
    val message : String

    data object Unknown: Error {
        override val message = "An unknown error occurred"
    }
    data object Foo: Error {
        override val message: String = "Foo error occurred"
    }
}