package com.example.retrofit.data

import com.example.retrofit.domain.Repository
import com.example.retrofit.data.retrofit.AuthRequest
import com.example.retrofit.data.retrofit.MainApi
import com.example.retrofit.data.retrofit.Product
import com.example.retrofit.data.retrofit.Products
import com.example.retrofit.data.retrofit.WeatherApi
import com.example.retrofit.data.retrofit.weather_models.WeatherDataModel
import retrofit2.Response
import retrofit2.http.Query
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val mainApi: MainApi,
    private val weatherApi: WeatherApi
) : GlobalRepository {

    override suspend fun auth(authRequest: AuthRequest): Response<User> {
        return mainApi.auth(authRequest)
    }

    override suspend fun getProductsByName(token: String, name: String): Response<Products> {
        return mainApi.getProductsByName(token, name)
    }

    override suspend fun getProductById(token: String, id: Int): Response<Product> {
        return mainApi.getProductById(token, id)
    }

    override suspend fun getProducts(token: String): Response<Products> {
        return mainApi.getProducts(token)
    }

    override suspend fun getWeatherData(
        key: String,
        q: String,
        days: String,
        aqi: String,
        alerts: String
    ): Response<WeatherDataModel> {
        return weatherApi.getWeatherData(key, q, days, aqi, alerts)
    }


}

interface WeatherRepository {
    suspend fun getWeatherData(
        key: String,
        q: String,
        days: String,
        aqi: String,
        alerts: String
    ): Response<WeatherDataModel>
}

interface GlobalRepository : Repository, WeatherRepository