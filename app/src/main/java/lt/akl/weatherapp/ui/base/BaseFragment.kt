package lt.akl.weatherapp.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import lt.akl.weatherapp.di.component.ActivityComponent
import lt.akl.weatherapp.utilities.extensions.getAppComponent

abstract class BaseFragment : Fragment() {
    protected var activityComponent: ActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = context.getAppComponent().activityComponent()
    }

    override fun onDestroy() {
        activityComponent = null
        super.onDestroy()
    }
}