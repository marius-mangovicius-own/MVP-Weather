package lt.akl.weatherapp.ui.now

import io.reactivex.Observable
import lt.akl.weatherapp.data.DataManager
import lt.akl.weatherapp.data.model.CurrentWeather
import lt.akl.weatherapp.data.model.DisplayLocation
import lt.akl.weatherapp.data.model.ImageUrl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.mockito.Mockito.`when` as whenever


@RunWith(RobolectricTestRunner::class)
class NowPresenterTest {

    @Mock
    private lateinit var activity: NowView;

    @Mock
    private lateinit var dataManager: DataManager

    private lateinit var presenter: NowPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = NowPresenter(dataManager);
        presenter.bind(activity);
    }

    @Test
    fun shouldFetchJokesWhenRequested() {
        val currentWeather = CurrentWeather(DisplayLocation(""), "", "", "",
                "", "", "", "", ImageUrl(""))
        whenever(dataManager.getDetailedWeather()).thenReturn(Observable.just(currentWeather))
        presenter.fetchDetailedWeatherInfo()
        verify(dataManager).getDetailedWeather()
    }
}