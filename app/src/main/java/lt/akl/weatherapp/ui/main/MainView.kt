package lt.akl.weatherapp.ui.main

import lt.akl.weatherapp.data.model.LocationSearch
import lt.akl.weatherapp.ui.base.MvpView

interface MainView : MvpView {

    fun onFetchSearchSuccess(results: List<LocationSearch>)

    fun onFetchError(error: Throwable)
}