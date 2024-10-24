package com.example.retrofit.domain

import org.json.JSONObject
import retrofit2.Response

suspend fun <T>retrofitError(response: Response<T>): Result<T> {
    val error = response.errorBody()?.string()?.let {
        JSONObject(it).getString("message")
    }
    return Result.failure(ErrorBodyException(error))
}