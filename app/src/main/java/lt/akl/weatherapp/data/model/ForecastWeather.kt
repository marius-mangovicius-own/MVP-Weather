package lt.akl.weatherapp.data.model

data class ForecastWeather(val simpleforecast: Forecast)

data class Forecast(val forecastday: List<DailyForecast>)

data class DailyForecast(val conditions: String,
                         val high: High,
                         val low: Low,
                         val date: Date,
                         val icon_url: String)

data class Date(val weekday: String)

data class High(val celsius: String)

data class Low(val celsius: String)
