package lt.akl.weatherapp.ui.now

import lt.akl.weatherapp.data.model.CurrentWeather
import lt.akl.weatherapp.ui.base.MvpView

interface NowView : MvpView {

    fun onFetchDetailedInfoSuccess(currentWeather: CurrentWeather)

    fun onFetchError(error: Throwable)
}