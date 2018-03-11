package lt.akl.weatherapp.ui.hourly

import lt.akl.weatherapp.data.model.HourlyForecast
import lt.akl.weatherapp.ui.base.MvpView

interface HourlyView : MvpView {

    fun onFetchHourlyForecastSuccess(hourlyForecast: List<HourlyForecast>)

    fun onFetchError(error: Throwable)
}