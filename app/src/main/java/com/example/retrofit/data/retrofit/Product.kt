package com.example.retrofit.data.retrofit


// TODO (entity)
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
) {
    companion object {
        fun build() = Product(
            id = 1,
            title = "",
            description = "",
            price = 1,
            discountPercentage = 1f,
            rating = 1f,
            stock = 1,
            brand = "",
            category = "",
            thumbnail = "",
            images = listOf()
        )
    }

}