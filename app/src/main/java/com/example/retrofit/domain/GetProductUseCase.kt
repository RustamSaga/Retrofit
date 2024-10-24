package com.example.retrofit.domain

import com.example.retrofit.data.retrofit.Product
import org.json.JSONObject
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun getProduct(token: String): Result<Product> {
        return try {
            val response = repository.getProductById(token, 3)
            val product = response.body()
            if (response.isSuccessful && product != null) {
                Result.success(product)
            } else {
                retrofitError(response)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}