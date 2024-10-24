package com.example.retrofit.data.retrofit

import com.example.retrofit.data.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// TODO (REST API)
interface MainApi {

    @Headers("Content-Type: application/json")
    @GET("auth/products/{id}")
    suspend fun getProductById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<Product>

    @Headers("Content-Type: application/json")
    @GET("auth/products")
    suspend fun getProducts(@Header("Authorization") token: String): Response<Products>

    @Headers("Content-Type: application/json")
    @GET("auth/products/search")
    suspend fun getProductsByName(
        @Header("Authorization") token: String,
        @Query("q") name: String
    ): Response<Products>


    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest): Response<User>

    /**
     * fetch('https://dummyjson.com/auth/login', {
     *   method: 'POST',
     *   headers: { 'Content-Type': 'application/json' },
     *   body: JSON.stringify({
     *
     *     username: 'kminchelle',
     *     password: '0lelplR',
     *     // expiresInMins: 60, // optional
     *   })
     * })
     * .then(res => res.json())
     * .then(console.log);
     *
     */
}