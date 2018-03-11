package lt.akl.weatherapp.data.model

data class CurrentWeatherResponse<out T>(val type: String, val current_observation: CurrentWeather)

data class ForecastResponse<out T>(val type: String, val forecast: ForecastWeather)

data class HourlyResponse<out T>(val type: String, val hourly_forecast: List<HourlyForecast>)

data class AutocompleteResponse<out T>(val type: String, val RESULTS: List<LocationSearch>)
