package com.example.retrofit.domain

import com.example.retrofit.data.WeatherRepository
import com.example.retrofit.data.retrofit.weather_models.WeatherDataModel
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend fun getWeather(city: String, days: String) : Result<WeatherDataModel> {
        return try {
            val response = repository.getWeatherData(
                key = "71905cf4f2ab40da9e8142341222207",
                q = city,
                days = days,
                aqi = "no",
                alerts = "no"
            )
            val model = response.body()
            if (response.isSuccessful && model != null) {
                Result.success(model)
            } else {
                retrofitError(response)
            }
        }catch (e: Exception) {
            Result.failure(e)
        }

    }
}