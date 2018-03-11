package lt.akl.weatherapp.ui.now

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_now.*
import lt.akl.weatherapp.R
import lt.akl.weatherapp.data.model.CurrentWeather
import lt.akl.weatherapp.ui.base.BaseFragment
import lt.akl.weatherapp.ui.main.Updatable
import lt.akl.weatherapp.utilities.extensions.hide
import javax.inject.Inject

class NowFragment : BaseFragment(), NowView, Updatable {

    companion object {
        val TAG = NowFragment::class.java.simpleName!!
    }

    @Inject
    lateinit var presenter: NowPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_now, container, false)
        activityComponent?.inject(this)
        presenter.fetchDetailedWeatherInfo()
        presenter.bind(this)
        return rootView
    }

    override fun onFetchError(error: Throwable) {
        if (!isVisible) {
            Log.i(TAG, "not visible")
            return
        }
        progressBar.hide()
        Log.e(TAG, error.message)
    }

    override fun onFetchDetailedInfoSuccess(currentWeather: CurrentWeather) {
        if (!isVisible) {
            Log.i(TAG, "not visible")
            return
        }
        progressBar.hide()
        syncUi(currentWeather)
    }

    override fun update() {
        if (!isVisible) {
            Log.i(TAG, "not visible")
            return
        }
        presenter.fetchDetailedWeatherInfo()
    }

    private fun syncUi(currentWeather: CurrentWeather) {
        Glide.with(context).load(currentWeather.image.url).asBitmap().into(imageNow)
        Glide.with(context).load(currentWeather.icon_url).asBitmap().into(imageIconNow)
        tempC.text = getString(R.string.temp_with_celsius_sign, currentWeather.temp_c)
        weatherCondition.text = currentWeather.weather
        location.text = currentWeather.display_location.full
        humidityNow.text = getString(R.string.humidity, currentWeather.relative_humidity)
        windSpeedNow.text = getString(R.string.wind_speed_kph, currentWeather.wind_kph)
        feelsLikeNow.text = getString(R.string.feels_like, currentWeather.feelslike_c)
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }
}
