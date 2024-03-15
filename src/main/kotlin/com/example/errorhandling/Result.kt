package com.example.errorhandling

sealed interface Result<T> {
    val data: T?
    val error: Error?

    val isSuccess: Boolean
        get() = data != null

    val isFailure: Boolean
        get() = error != null

    fun <R> map(transform: (d: T) -> R): Result<R> =
        if (isSuccess) Success(transform(data!!)) else Failure(error!!)

    fun <R : Error> mapError(transform: (e: Error) -> R): Result<T> =
        if (isFailure) Failure(transform(error!!)) else this

    fun <R> fold(onSuccess: (d: T) -> R, onFailure: (e: Error) -> R): R =
        if (isSuccess) onSuccess(data!!) else onFailure(error!!)

    companion object {
        fun <T> success(data: T): Result<T> =
            Success(data)

        fun <T> failure(error: Error): Result<T> =
            Failure(error)
    }
}

data class Success<T>(override val data: T) : Result<T> {
    override val error: Error? = null
}

data class Failure<T>(override val error: Error) : Result<T> {
    override val data: T? = null
}