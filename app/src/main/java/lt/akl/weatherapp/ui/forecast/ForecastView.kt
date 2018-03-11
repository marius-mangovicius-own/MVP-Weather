package lt.akl.weatherapp.ui.forecast

import lt.akl.weatherapp.data.model.DailyForecast
import lt.akl.weatherapp.ui.base.MvpView

interface ForecastView : MvpView {

    fun onFetchForecastSuccess(dailyForecast: List<DailyForecast>)

    fun onFetchError(error: Throwable)
}