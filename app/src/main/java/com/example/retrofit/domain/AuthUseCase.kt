package com.example.retrofit.domain

import com.example.retrofit.data.User
import com.example.retrofit.data.retrofit.AuthRequest
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun auth(authRequest: AuthRequest): Result<User> {
        return try {
            val response = repository.auth(authRequest)
            val user = response.body()
            if (response.isSuccessful && user != null) {
                Result.success(user)
            } else {
                retrofitError(response)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}

class ErrorBodyException(message: String?): Throwable(message)