package lt.akl.weatherapp.data

import io.reactivex.Observable
import lt.akl.weatherapp.data.model.CurrentWeather
import lt.akl.weatherapp.data.model.DailyForecast
import lt.akl.weatherapp.data.model.HourlyForecast
import lt.akl.weatherapp.data.model.LocationSearch
import lt.akl.weatherapp.data.remote.SearchService
import lt.akl.weatherapp.data.remote.WeatherService
import lt.akl.weatherapp.di.qualifier.Search
import lt.akl.weatherapp.di.qualifier.Weather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DataManagerImpl @Inject constructor(@Weather private val weatherService: WeatherService,
                                                   @Search private val searchService: SearchService) : DataManager {

    override fun getSearchResults(query: String): Observable<List<LocationSearch>> {
        return searchService
                .fetchLocationSearch(query)
                .flatMap { Observable.just(it.RESULTS) }
    }

    override fun getHourlyForecast(): Observable<List<HourlyForecast>> {
        return weatherService
                .fetchHourlyForecast()
                .flatMap { Observable.just(it.hourly_forecast) }
    }

    override fun getDailyForecast(): Observable<List<DailyForecast>> {
        return weatherService
                .fetchForecast()
                .flatMap { Observable.just(it.forecast.simpleforecast.forecastday) }
    }

    override fun getDetailedWeather(): Observable<CurrentWeather> {
        return weatherService
                .fetchDetailedWeather()
                .flatMap { Observable.just(it.current_observation) }
    }
}