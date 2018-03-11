package lt.akl.weatherapp.data.model

data class CurrentWeather(val display_location: DisplayLocation,
                          val wind_kph: String,
                          val wind_dir: String,
                          val temp_c: String,
                          val weather: String,
                          val relative_humidity: String,
                          val feelslike_c: String,
                          val icon_url: String,
                          val image: ImageUrl)

data class DisplayLocation(val full: String)

data class ImageUrl(val url: String)
