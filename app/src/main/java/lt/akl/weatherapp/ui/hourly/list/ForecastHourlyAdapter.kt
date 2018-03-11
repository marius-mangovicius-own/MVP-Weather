package lt.akl.weatherapp.ui.hourly.list

import android.content.Context
import lt.akl.weatherapp.data.model.HourlyForecast
import lt.akl.weatherapp.di.scope.PerView
import lt.akl.weatherapp.ui.base.list.BaseListAdapter
import lt.akl.weatherapp.ui.base.list.BaseViewHolder
import javax.inject.Inject

@PerView
class ForecastHourlyAdapter @Inject constructor() : BaseListAdapter<HourlyForecast>() {

    override fun getListItemView(context: Context): BaseViewHolder<HourlyForecast> {
        return ForecastHourlyViewHolder(context)
    }
}