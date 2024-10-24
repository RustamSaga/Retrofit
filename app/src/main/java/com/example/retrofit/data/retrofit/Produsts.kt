package com.example.retrofit.data.retrofit

import com.example.retrofit.data.retrofit.Product

data class Products(
    val products: List<Product> = emptyList(),
    val total: Int = -1,
    val skip: Int = -1,
    val limit: Int = -1
) {
}