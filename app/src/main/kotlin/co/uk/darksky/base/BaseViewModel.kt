package co.uk.darksky.base

import android.arch.lifecycle.ViewModel
import co.uk.darksky.injection.component.ViewModelInjector
import co.uk.darksky.injection.module.NetworkModule
import co.uk.darksky.ui.forecast.ForecastViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = co.uk.darksky.injection.component.DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ForecastViewModel -> injector.inject(this)
        }
    }
}