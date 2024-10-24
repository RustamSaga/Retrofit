package com.example.retrofit.domain

import com.example.retrofit.data.retrofit.Products
import org.json.JSONObject
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun getProducts(token: String): Result<Products> {
        return try {
            val response = repository.getProducts(token)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Result.success(result)
            } else {
                retrofitError(response)
            }
        }catch (e: Exception) {
            Result.failure(e)
        }
    }
}