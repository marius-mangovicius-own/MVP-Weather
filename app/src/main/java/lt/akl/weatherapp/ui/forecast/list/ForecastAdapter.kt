package lt.akl.weatherapp.ui.forecast.list

import android.content.Context
import lt.akl.weatherapp.data.model.DailyForecast
import lt.akl.weatherapp.di.scope.PerView
import lt.akl.weatherapp.ui.base.list.BaseListAdapter
import lt.akl.weatherapp.ui.base.list.BaseViewHolder
import javax.inject.Inject

@PerView
class ForecastAdapter @Inject constructor() : BaseListAdapter<DailyForecast>() {

    override fun getListItemView(context: Context): BaseViewHolder<DailyForecast> {
        return ForecastViewHolder(context)
    }
}