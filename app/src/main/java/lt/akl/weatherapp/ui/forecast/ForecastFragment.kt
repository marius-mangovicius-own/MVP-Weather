package lt.akl.weatherapp.ui.forecast

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_forecast.*
import kotlinx.android.synthetic.main.fragment_forecast.view.*
import lt.akl.weatherapp.R
import lt.akl.weatherapp.data.model.DailyForecast
import lt.akl.weatherapp.ui.base.BaseFragment
import lt.akl.weatherapp.ui.forecast.list.ForecastAdapter
import lt.akl.weatherapp.ui.main.Updatable
import lt.akl.weatherapp.utilities.DividerItemDecoration
import lt.akl.weatherapp.utilities.extensions.dpToPx
import lt.akl.weatherapp.utilities.extensions.hide
import javax.inject.Inject


class ForecastFragment : BaseFragment(), ForecastView, Updatable {

    companion object {
        val TAG = ForecastFragment::class.java.simpleName!!
    }

    @Inject
    lateinit var presenter: ForecastPresenter

    @Inject
    lateinit var adapter: ForecastAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_forecast, container, false)
        activityComponent?.inject(this)
        setupRecycler(rootView)
        presenter.fetchForecast()
        presenter.bind(this)
        return rootView
    }

    private fun setupRecycler(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        view.recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL, 8.dpToPx(context)))
        view.recyclerView.adapter = adapter
    }

    override fun onFetchForecastSuccess(dailyForecast: List<DailyForecast>) {
        if (!isVisible) {
            Log.i(TAG, "not visible")
            return
        }
        progressBar.hide()
        adapter.clearItems()
        adapter.addItems(dailyForecast)
    }

    override fun onFetchError(error: Throwable) {
        if (!isVisible) {
            Log.i(TAG, "not visible")
            return
        }
        progressBar.hide()
        Toast.makeText(activity, "Error. ${error.message.toString()}", Toast.LENGTH_LONG).show()
    }

    override fun update() {
        if (!isVisible) {
            Log.i(TAG, "not visible")
            return
        }
        presenter.fetchForecast()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }
}