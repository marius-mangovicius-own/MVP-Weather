package lt.akl.weatherapp.utilities.extensions

import android.content.Context
import lt.akl.weatherapp.MainApp
import lt.akl.weatherapp.di.component.AppComponent

fun Context.getAppComponent(): AppComponent = (applicationContext as MainApp).appComponent


