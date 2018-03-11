package lt.akl.weatherapp.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import lt.akl.weatherapp.ui.forecast.ForecastFragment
import lt.akl.weatherapp.ui.hourly.HourlyFragment
import lt.akl.weatherapp.ui.now.NowFragment

class WeatherPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val now = "Now"
    private val hourly = "Hourly"
    private val forecast = "Forecast"

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> NowFragment()
            1 -> HourlyFragment()
            2 -> ForecastFragment()
            else -> null
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getItemPosition(obj: Any?): Int {
        when (obj) {
            is NowFragment -> obj.update()
            is HourlyFragment -> obj.update()
            is ForecastFragment -> obj.update()
        }
        return super.getItemPosition(obj)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return now
            1 -> return hourly
            2 -> return forecast
        }
        return null
    }
}