package lt.akl.weatherapp.di.component

import dagger.Component
import lt.akl.weatherapp.di.module.Bindings
import lt.akl.weatherapp.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(Bindings::class), (NetworkModule::class)])
interface AppComponent {

    fun activityComponent(): ActivityComponent
}