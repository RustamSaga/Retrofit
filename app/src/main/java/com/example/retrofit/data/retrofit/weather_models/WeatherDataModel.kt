package com.example.retrofit.data.retrofit.weather_models


/**
 * data class'ы создавались учитывая данные resource.json
 */
data class WeatherDataModel(
    val location: LocationDataModel,
    val current: CurrentModel,
    val forecast: ForecastModel
)

/**
 *  * необязательно указывать все поля
 *  * обязательно наименования должны совпадать
 */
data class LocationDataModel(
    val name: String,
    val localtime: String
)

data class CurrentModel(
    val last_updated: String,
    val temp_c: Float,
    val condition: ConditionModel
)

data class ConditionModel(
    val text: String,
    val icon: String
)

data class ForecastModel(
    val forecastday: List<ForecastDayModel>
)

data class ForecastDayModel(
    val date: String,
    val day: DayModel,
    val hour: List<HourModel>
)

data class DayModel(
    val maxtemp_c: String,
    val mintemp_c: String,
    val condition: ConditionModel
)

data class HourModel(
    val time: String,
    val temp_c: Float,
    val condition: ConditionModel
)