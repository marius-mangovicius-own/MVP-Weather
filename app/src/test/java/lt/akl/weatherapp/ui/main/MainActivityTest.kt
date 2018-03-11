package lt.akl.weatherapp.ui.main

import android.support.v7.widget.Toolbar
import android.view.View
import junit.framework.Assert.assertTrue
import kotlinx.android.synthetic.main.activity_main.*
import lt.akl.weatherapp.data.DataManager
import lt.akl.weatherapp.data.model.LocationSearch
import lt.akl.weatherapp.utilities.LocationKeyProvider
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast
import org.hamcrest.Matchers.`is` as Is


@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Mock
    private lateinit var dataManager: DataManager
    private lateinit var activity: MainActivity
    private lateinit var presenter: MainPresenter
    private lateinit var toolbar: Toolbar

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
        presenter = MainPresenter(dataManager)
        presenter.bind(activity)
        findViews()
    }

    private fun findViews() {
        toolbar = activity.toolbar
    }

    @Test
    fun toolbarShouldBePresentAndVisible() {
        assertTrue(toolbar.visibility == View.VISIBLE)
    }

    @Test
    fun shouldShowErrorToastWhenPresenterNotifiesError() {
        activity.onFetchError(Throwable("Error"))
        assertThat(ShadowToast.shownToastCount(), Is(1))
    }

    @Test
    fun shouldUpdateLocationKeyWhenPresenterNotifiesSuccess() {
        val locationSearch = LocationSearch("test", "test")
        activity.onFetchSearchSuccess(listOf(locationSearch))
        assertTrue(LocationKeyProvider.locationKey == "test")
    }

}