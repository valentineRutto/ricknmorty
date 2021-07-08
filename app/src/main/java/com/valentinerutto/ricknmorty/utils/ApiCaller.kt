package com.valentinerutto.ricknmorty.utils

/*
 * Copyright 2020 d.light Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
): NetworkResult<T> {
    return withContext(dispatcher) {
        try {
            NetworkResult.Loading
            NetworkResult.Success(apiCall.invoke())

        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> NetworkResult.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    NetworkResult.ServerError(code, errorResponse)
                }
                else -> {
                    NetworkResult.ServerError(null, null)
                }
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.charStream()?.let {
            val gson = GsonBuilder()
                    .create()
            gson.fromJson(it, ErrorResponse::class.java)
        }
    } catch (exception: Exception) {
        null
    }
}

inline fun <T> getResult(
        response: Response<List<T>>,
        onError: () -> NetworkResult.ServerError
): NetworkResult<List<T>> {
    if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            return NetworkResult.Success(body)
        }
    } else {
    }

    return onError.invoke()
}
//
//fun <D : Any> getNetworkResult(
//        response: NetworkResponse<D, ErrorResponse>,
//): NetworkResult<D> {
//    return when (response) {
//        is NetworkResponse.Success -> {
//            NetworkResult.Success(response.body)
//        }
//        is NetworkResponse.ServerError -> {
//            NetworkResult.ServerError(response.code, response.body)
//        }
//        is NetworkResponse.NetworkError -> {
//            NetworkResult.NetworkError
//        }
//        is NetworkResponse.UnknownError -> {
//            NetworkResult.NetworkError
//        }
//    }
//}
