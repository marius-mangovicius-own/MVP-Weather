package lt.akl.weatherapp.ui.hourly

import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import lt.akl.weatherapp.data.DataManager
import lt.akl.weatherapp.data.model.HourlyForecast
import lt.akl.weatherapp.di.scope.PerView
import lt.akl.weatherapp.ui.base.BasePresenter
import javax.inject.Inject

@PerView
class HourlyPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<HourlyView>() {

    fun fetchHourlyForecast() {
        disposables.add(
                dataManager.getHourlyForecast()
                        .subscribeOn(io())
                        .observeOn(mainThread())
                        .subscribe(
                                { onFetchForecastSuccess(it) },
                                { onFetchError(it) })
        )
    }

    private fun onFetchForecastSuccess(hourlyForecast: List<HourlyForecast>) {
        view?.onFetchHourlyForecastSuccess(hourlyForecast)
    }

    private fun onFetchError(error: Throwable) {
        view?.onFetchError(error)
    }
}
