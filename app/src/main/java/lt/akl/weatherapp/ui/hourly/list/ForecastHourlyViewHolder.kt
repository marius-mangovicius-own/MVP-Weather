package lt.akl.weatherapp.ui.hourly.list

import android.content.Context
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_forecast_hourly.view.*
import lt.akl.weatherapp.R
import lt.akl.weatherapp.data.model.HourlyForecast
import lt.akl.weatherapp.ui.base.list.BaseViewHolder

class ForecastHourlyViewHolder(context: Context) : BaseViewHolder<HourlyForecast>(context) {

    override fun layoutResId(): Int = R.layout.view_forecast_hourly

    override fun bind(item: HourlyForecast) {
        Glide.with(context).load(item.icon_url).asBitmap().into(imageViewHourly)
        val time = item.FCTTIME.hour + ":" + item.FCTTIME.min
        currentHourHourly.text = time
        weatherHourly.text = item.condition
        tempHourly.text = context.getString(R.string.temp_with_celsius_sign, item.temp.metric)
    }
}
