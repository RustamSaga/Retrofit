package com.example.retrofit.screens.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.data.retrofit.WeatherApi
import com.example.retrofit.domain.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCase: GetWeatherUseCase
): ViewModel() {
    fun get(result: (String, String) -> Unit) {
        viewModelScope.launch {
            try {
                val def = async(Dispatchers.IO) {
                    useCase.getWeather("London", "3")
                }
                def.await().onSuccess {
                    result(it.current.temp_c.toString(), it.location.localtime)
                }.onFailure {
                    result(it.message?: "", "")
                }
            }catch (e: Exception) {
                result(e.toString(), "")
            }

        }
    }

}