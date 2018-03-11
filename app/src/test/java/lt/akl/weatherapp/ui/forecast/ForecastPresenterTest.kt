package lt.akl.weatherapp.ui.forecast

import io.reactivex.Observable
import lt.akl.weatherapp.data.DataManager
import lt.akl.weatherapp.data.model.DailyForecast
import lt.akl.weatherapp.data.model.Date
import lt.akl.weatherapp.data.model.High
import lt.akl.weatherapp.data.model.Low
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.mockito.Mockito.`when` as whenever


@RunWith(RobolectricTestRunner::class)
class ForecastPresenterTest {

    @Mock
    private lateinit var activity: ForecastView;

    @Mock
    private lateinit var dataManager: DataManager

    private lateinit var presenter: ForecastPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = ForecastPresenter(dataManager);
        presenter.bind(activity);
    }

    @Test
    fun shouldFetchJokesWhenRequested() {
        val dailyForecast = DailyForecast("", High(""), Low(""), Date(""), "")
        whenever(dataManager.getDailyForecast()).thenReturn(Observable.just(listOf(dailyForecast)))
        presenter.fetchForecast()
        verify(dataManager).getDailyForecast()
    }
}