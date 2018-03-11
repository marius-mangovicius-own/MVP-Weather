package lt.akl.weatherapp.ui.hourly

import android.app.Activity
import android.widget.TextView
import kotlinx.android.synthetic.main.view_forecast_hourly.view.*
import lt.akl.weatherapp.data.model.HourlyForecast
import lt.akl.weatherapp.data.model.Temp
import lt.akl.weatherapp.data.model.TimeHour
import lt.akl.weatherapp.ui.hourly.list.ForecastHourlyViewHolder
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class ForecastHourlyViewHolderTest {

    private lateinit var forecastViewHolder: ForecastHourlyViewHolder
    private lateinit var weatherHourlyTextView: TextView
    private lateinit var currentHourHourlyTextView: TextView

    @Before
    fun setUp() {
        val activity = Robolectric.buildActivity(Activity::class.java).create().get()
        forecastViewHolder = ForecastHourlyViewHolder(activity)
        findViews()
    }

    private fun findViews() {
        weatherHourlyTextView = forecastViewHolder.weatherHourly
        currentHourHourlyTextView = forecastViewHolder.currentHourHourly
    }

    @Test
    fun shouldSetDataProperly() {
        val hourlyForecast = HourlyForecast("sunny", "url", Temp("20C"),
                TimeHour("4", "20"))
        forecastViewHolder.bind(hourlyForecast)
        assertTrue(weatherHourlyTextView.text == hourlyForecast.condition)
        assertTrue(currentHourHourlyTextView.text == hourlyForecast.FCTTIME.hour + ":" + hourlyForecast.FCTTIME.min)
    }
}