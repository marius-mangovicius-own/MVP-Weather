package lt.akl.weatherapp.ui.main

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import lt.akl.weatherapp.R
import lt.akl.weatherapp.data.model.LocationSearch
import lt.akl.weatherapp.ui.base.BaseActivity
import lt.akl.weatherapp.utilities.LocationKeyProvider
import javax.inject.Inject


open class MainActivity : BaseActivity(), MainView {
    private lateinit var weatherPageAdapter: WeatherPageAdapter

    @Inject
    lateinit var presenter: MainPresenter

    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent?.inject(this)
        setSupportActionBar(toolbar)
        weatherPageAdapter = WeatherPageAdapter(supportFragmentManager)
        container.adapter = weatherPageAdapter
        tabLayout.setupWithViewPager(container)
        presenter.bind(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val searchViewItem = menu.findItem(R.id.menu_search)
        val searchViewAndroidActionBar = MenuItemCompat.getActionView(searchViewItem) as SearchView

        searchViewAndroidActionBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchViewAndroidActionBar.clearFocus()
                presenter.fetchSearchForecast(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onFetchError(error: Throwable) {
        Toast.makeText(this, "Error. ${error.message.toString()}", Toast.LENGTH_LONG).show()
    }

    override fun onFetchSearchSuccess(results: List<LocationSearch>) {
        LocationKeyProvider.locationKey = results[0].zmw
        weatherPageAdapter.notifyDataSetChanged()
    }
}
