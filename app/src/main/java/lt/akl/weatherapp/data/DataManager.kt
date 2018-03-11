package lt.akl.weatherapp.data

import io.reactivex.Observable
import lt.akl.weatherapp.data.model.CurrentWeather
import lt.akl.weatherapp.data.model.DailyForecast
import lt.akl.weatherapp.data.model.HourlyForecast
import lt.akl.weatherapp.data.model.LocationSearch

interface DataManager {

    fun getDailyForecast(): Observable<List<DailyForecast>>

    fun getHourlyForecast(): Observable<List<HourlyForecast>>

    fun getDetailedWeather(): Observable<CurrentWeather>

    fun getSearchResults(query: String): Observable<List<LocationSearch>>

}