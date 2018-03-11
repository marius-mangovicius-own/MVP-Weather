package lt.akl.weatherapp.di.component

import dagger.Subcomponent
import lt.akl.weatherapp.di.scope.PerView
import lt.akl.weatherapp.ui.forecast.ForecastFragment
import lt.akl.weatherapp.ui.hourly.HourlyFragment
import lt.akl.weatherapp.ui.main.MainActivity
import lt.akl.weatherapp.ui.now.NowFragment

@PerView
@Subcomponent
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: ForecastFragment)

    fun inject(fragment: NowFragment)

    fun inject(fragment: HourlyFragment)
}