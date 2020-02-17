package com.example.githubrepos.api

import retrofit2.Response

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result(Result.Status.SUCCESS, response.body(), null)
            }
        }
        return error("${response.code()} ${response.message()}")
    }


    private fun <T> error(message: String): Result<T> {
        return Result(Result.Status.ERROR, null, message)
    }

}