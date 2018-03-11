package lt.akl.weatherapp.data.remote

import io.reactivex.Observable
import lt.akl.weatherapp.data.model.AutocompleteResponse
import lt.akl.weatherapp.data.model.LocationSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/aq")
    fun fetchLocationSearch(@Query("query") query: String): Observable<AutocompleteResponse<LocationSearch>>
}