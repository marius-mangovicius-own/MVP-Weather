package lt.akl.weatherapp.ui.main

import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import lt.akl.weatherapp.data.DataManager
import lt.akl.weatherapp.data.model.LocationSearch
import lt.akl.weatherapp.di.scope.PerView
import lt.akl.weatherapp.ui.base.BasePresenter
import javax.inject.Inject

@PerView
class MainPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<MainView>() {

    fun fetchSearchForecast(query: String) {
        disposables.add(
                dataManager.getSearchResults(query)
                        .subscribeOn(io())
                        .observeOn(mainThread())
                        .subscribe(
                                { onFetchSearchSuccess(it) },
                                { onFetchError(it) })
        )
    }

    private fun onFetchSearchSuccess(results: List<LocationSearch>) {
        view?.onFetchSearchSuccess(results)
    }

    private fun onFetchError(error: Throwable) {
        view?.onFetchError(error)
    }
}
