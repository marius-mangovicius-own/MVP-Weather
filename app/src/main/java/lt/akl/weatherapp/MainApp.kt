package lt.akl.weatherapp

import android.app.Application
import lt.akl.weatherapp.di.component.AppComponent
import lt.akl.weatherapp.di.component.DaggerAppComponent

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        setupComponent()
    }

    private fun setupComponent() {
        appComponent = DaggerAppComponent.builder().build()
    }
}