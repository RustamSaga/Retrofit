package com.example.retrofit.domain

import com.example.retrofit.data.User
import com.example.retrofit.data.retrofit.AuthRequest
import com.example.retrofit.data.retrofit.Product
import com.example.retrofit.data.retrofit.Products
import retrofit2.Response

interface Repository {

    suspend fun auth(authRequest: AuthRequest): Response<User>

    suspend fun getProductsByName(token: String, name: String): Response<Products>

    suspend fun getProductById(token: String, id: Int): Response<Product>

    suspend fun getProducts(token: String): Response<Products>

}