package lt.akl.weatherapp.ui.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T : MvpView> {

    protected val disposables = CompositeDisposable()
    protected var view: T? = null

    fun bind(view: T) {
        this.view = view
    }

    private fun unbind() {
        this.view = null
    }

    fun destroy() {
        disposables.clear()
        unbind()
    }
}