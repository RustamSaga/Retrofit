package com.example.retrofit.domain

import com.example.retrofit.data.retrofit.Products
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend fun search(token: String, name: String) : Result<Products> {
        return try {
            val response = repository.getProductsByName(token, name)
            val products = response.body()
            if (response.isSuccessful && products != null) {
                Result.success(products)
            } else {
                retrofitError(response)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}