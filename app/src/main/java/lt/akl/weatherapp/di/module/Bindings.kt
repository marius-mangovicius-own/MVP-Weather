package lt.akl.weatherapp.di.module

import dagger.Binds
import dagger.Module
import lt.akl.weatherapp.data.DataManager
import lt.akl.weatherapp.data.DataManagerImpl

@Module
abstract class Bindings {

    @Binds
    internal abstract fun bindDataManger(manager: DataManagerImpl): DataManager
}