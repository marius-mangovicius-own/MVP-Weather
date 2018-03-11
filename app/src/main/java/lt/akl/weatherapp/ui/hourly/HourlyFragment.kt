package lt.akl.weatherapp.ui.hourly

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_forecast.view.*
import kotlinx.android.synthetic.main.fragment_forecast_hourly.*
import lt.akl.weatherapp.R
import lt.akl.weatherapp.data.model.HourlyForecast
import lt.akl.weatherapp.ui.base.BaseFragment
import lt.akl.weatherapp.ui.hourly.list.ForecastHourlyAdapter
import lt.akl.weatherapp.ui.main.Updatable
import lt.akl.weatherapp.utilities.DividerItemDecoration
import lt.akl.weatherapp.utilities.extensions.dpToPx
import lt.akl.weatherapp.utilities.extensions.hide
import javax.inject.Inject

class HourlyFragment : BaseFragment(), HourlyView, Updatable {

    companion object {
        val TAG = HourlyFragment::class.java.simpleName!!
    }

    @Inject
    lateinit var presenter: HourlyPresenter

    @Inject
    lateinit var adapter: ForecastHourlyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_forecast_hourly, container, false)
        activityComponent?.inject(this)
        setupRecycler(rootView)
        presenter.fetchHourlyForecast()
        presenter.bind(this)
        return rootView
    }

    private fun setupRecycler(view: View) {
        view.recyclerView.setHasFixedSize(true)
        view.recyclerView.layoutManager = LinearLayoutManager(context)
        view.recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL, 8.dpToPx(context)))
        view.recyclerView.adapter = adapter
    }

    override fun onFetchHourlyForecastSuccess(hourlyForecast: List<HourlyForecast>) {
        if (!isVisible) {
            Log.i(TAG, "not visible")
            return
        }
        progressBar.hide()
        adapter.clearItems()
        adapter.addItems(hourlyForecast)
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
        presenter.fetchHourlyForecast()
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }
}