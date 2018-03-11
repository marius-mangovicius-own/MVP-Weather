package lt.akl.weatherapp.ui.forecast.list

import android.content.Context
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_forecast.view.*
import lt.akl.weatherapp.R
import lt.akl.weatherapp.data.model.DailyForecast
import lt.akl.weatherapp.ui.base.list.BaseViewHolder

class ForecastViewHolder(context: Context) : BaseViewHolder<DailyForecast>(context) {

    override fun layoutResId(): Int = R.layout.view_forecast

    override fun bind(item: DailyForecast) {
        Glide.with(context).load(item.icon_url).asBitmap().into(imageViewForecast)
        dayOfWeekForecast.text = item.date.weekday
        weatherForecast.text = item.conditions
        tempForecast.text = context.getString(R.string.temp_with_celsius_sign_high_and_low, item.high.celsius, item.low.celsius)
    }
}
