package lt.akl.weatherapp.data.remote

import io.reactivex.Observable
import lt.akl.weatherapp.data.model.*
import lt.akl.weatherapp.di.module.NetworkModule
import lt.akl.weatherapp.utilities.LocationKeyProvider
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("/api/${NetworkModule.API_KEY}/hourly/q/zmw:{id}.json")
    fun fetchHourlyForecast(@Path("id") string: String =
                            LocationKeyProvider.locationKey): Observable<HourlyResponse<HourlyForecast>>

    @GET("/api/${NetworkModule.API_KEY}/conditions/q/zmw:{id}.json")
    fun fetchDetailedWeather(@Path("id") string: String =
                             LocationKeyProvider.locationKey): Observable<CurrentWeatherResponse<CurrentWeather>>

    @GET("/api/${NetworkModule.API_KEY}/forecast10day/q/zmw:{id}.json")
    fun fetchForecast(@Path("id") string: String =
                      LocationKeyProvider.locationKey): Observable<ForecastResponse<ForecastWeather>>
}