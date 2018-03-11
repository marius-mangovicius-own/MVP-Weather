package lt.akl.weatherapp.ui.forecast

import android.app.Activity
import android.widget.TextView
import kotlinx.android.synthetic.main.view_forecast.view.*
import lt.akl.weatherapp.data.model.DailyForecast
import lt.akl.weatherapp.data.model.Date
import lt.akl.weatherapp.data.model.High
import lt.akl.weatherapp.data.model.Low
import lt.akl.weatherapp.ui.forecast.list.ForecastViewHolder
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class ForecastViewHolderTest {

    private lateinit var forecastViewHolder: ForecastViewHolder
    private lateinit var dayOfWeekForecastTextView: TextView
    private lateinit var weatherForecastTextView: TextView

    @Before
    fun setUp() {
        val activity = Robolectric.buildActivity(Activity::class.java).create().get()
        forecastViewHolder = ForecastViewHolder(activity)
        findViews()
    }

    private fun findViews() {
        dayOfWeekForecastTextView = forecastViewHolder.dayOfWeekForecast
        weatherForecastTextView = forecastViewHolder.weatherForecast
    }

    @Test
    fun shouldSetDataProperly() {
        val dailyForecast = DailyForecast("sunny", High("20C"), Low("10C"),
                Date("Tuesday"), "url")
        forecastViewHolder.bind(dailyForecast)
        assertTrue(dayOfWeekForecastTextView.text == dailyForecast.date.weekday)
        assertTrue(weatherForecastTextView.text == dailyForecast.conditions)
    }
}