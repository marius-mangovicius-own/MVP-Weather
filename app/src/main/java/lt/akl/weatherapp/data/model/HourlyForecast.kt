package lt.akl.weatherapp.data.model

data class HourlyForecast(val condition: String,
                          val icon_url: String,
                          val temp: Temp,
                          val FCTTIME: TimeHour)

data class Temp(val metric: String)

data class TimeHour(val hour: String, val min: String)