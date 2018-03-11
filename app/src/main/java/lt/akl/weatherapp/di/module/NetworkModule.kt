package lt.akl.weatherapp.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import lt.akl.weatherapp.data.remote.SearchService
import lt.akl.weatherapp.data.remote.WeatherService
import lt.akl.weatherapp.di.qualifier.Search
import lt.akl.weatherapp.di.qualifier.Weather
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class NetworkModule {

    companion object {
        const val API_KEY = "5ae4e832b455282b"
        const val API_URL = "http://api.wunderground.com/"
        const val SEARCH_API_URL = "http://autocomplete.wunderground.com/"
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    @Weather
    open fun provideWeatherService(): WeatherService {
        val retrofit = Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    @Search
    open fun provideSearchService(): SearchService {
        val retrofit = Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl(SEARCH_API_URL)
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(SearchService::class.java)
    }

}
