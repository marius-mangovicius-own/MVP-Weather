package lt.akl.weatherapp.ui.main

import io.reactivex.Observable
import lt.akl.weatherapp.data.DataManager
import lt.akl.weatherapp.data.model.LocationSearch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.mockito.Mockito.`when` as whenever


@RunWith(RobolectricTestRunner::class)
class MainPresenterTest {

    @Mock
    private lateinit var activity: MainView;

    @Mock
    private lateinit var dataManager: DataManager

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = MainPresenter(dataManager);
        presenter.bind(activity);
    }

    @Test
    fun shouldFetchJokesWhenRequested() {
        val locationSearch = LocationSearch("name", "zmw")
        whenever(dataManager.getSearchResults(anyString())).thenReturn(Observable.just(listOf(locationSearch)))
        presenter.fetchSearchForecast(anyString())
        verify(dataManager).getSearchResults(anyString())
    }
}