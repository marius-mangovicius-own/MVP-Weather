package lt.akl.weatherapp.ui.forecast

import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import lt.akl.weatherapp.data.DataManager
import lt.akl.weatherapp.data.model.DailyForecast
import lt.akl.weatherapp.di.scope.PerView
import lt.akl.weatherapp.ui.base.BasePresenter
import javax.inject.Inject

@PerView
class ForecastPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<ForecastView>() {

    fun fetchForecast() {
        disposables.add(
                dataManager.getDailyForecast()
                        .subscribeOn(io())
                        .observeOn(mainThread())
                        .subscribe(
                                { onFetchForecastSuccess(it) },
                                { onFetchError(it) })
        )
    }

    private fun onFetchForecastSuccess(dailyForecast: List<DailyForecast>) {
        view?.onFetchForecastSuccess(dailyForecast)
    }

    private fun onFetchError(error: Throwable) {
        view?.onFetchError(error)
    }
}
