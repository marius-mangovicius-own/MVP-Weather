package lt.akl.weatherapp.ui.now

import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import lt.akl.weatherapp.data.DataManager
import lt.akl.weatherapp.data.model.CurrentWeather
import lt.akl.weatherapp.di.scope.PerView
import lt.akl.weatherapp.ui.base.BasePresenter
import javax.inject.Inject

@PerView
class NowPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<NowView>() {

    fun fetchDetailedWeatherInfo() {
        disposables.add(
                dataManager.getDetailedWeather()
                        .subscribeOn(io())
                        .observeOn(mainThread())
                        .subscribe(
                                { onFetchDetailedInfoSuccess(it) },
                                { onFetchError(it) })
        )
    }

    private fun onFetchDetailedInfoSuccess(currentWeather: CurrentWeather) {
        view?.onFetchDetailedInfoSuccess(currentWeather)
    }

    private fun onFetchError(error: Throwable) {
        view?.onFetchError(error)
    }
}
