package lt.akl.weatherapp.ui.hourly

import io.reactivex.Observable
import lt.akl.weatherapp.data.DataManager
import lt.akl.weatherapp.data.model.HourlyForecast
import lt.akl.weatherapp.data.model.Temp
import lt.akl.weatherapp.data.model.TimeHour
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.mockito.Mockito.`when` as whenever


@RunWith(RobolectricTestRunner::class)
class HourlyPresenterTest {

    @Mock
    private lateinit var activity: HourlyView;

    @Mock
    private lateinit var dataManager: DataManager

    private lateinit var presenter: HourlyPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = HourlyPresenter(dataManager);
        presenter.bind(activity);
    }

    @Test
    fun shouldFetchJokesWhenRequested() {
        val hourlyForecast = HourlyForecast("", "", Temp(""), TimeHour("", ""))
        whenever(dataManager.getHourlyForecast()).thenReturn(Observable.just(listOf(hourlyForecast)))
        presenter.fetchHourlyForecast()
        verify(dataManager).getHourlyForecast()
    }
}