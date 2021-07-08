package com.valentinerutto.ricknmorty.utils

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class NetworkResult<out R> {

    /**
     * This is used to represent successful responses (2xx response codes, non empty response bodies)
     */
    data class Success<out T>(val data: T) : NetworkResult<T>()

    /**
     * Used to represent Server errors (non 2xx status code)
     */
    data class ServerError(
        val code: Int? = null,
        val errorBody: ErrorResponse? = null
    ) : NetworkResult<Nothing>()

    /**
     * Used to represent connectivity errors (a request that didn't result in a response)
     */
    object NetworkError : NetworkResult<Nothing>()
//    data class NetworkError(val error: IOException) : NetworkResult<Nothing>()

    /**
     * A request that resulted in an error different from an IO or Server error.
     *
     * An example of such an error is JSON parsing exception thrown by a serialization library.
     */
//    data class UnknownError(val error: Throwable) : NetworkResult<Nothing>()

    /**
     * Used to represent Loading status of a network call
     */
    object Loading : NetworkResult<Nothing>()
}

/**
 * `true` if [NetworkResult] is of type [NetworkResult.Success] & holds non-null [NetworkResult.Success.data].
 */
val NetworkResult<*>.succeeded
    get() = this is NetworkResult.Success && data != null