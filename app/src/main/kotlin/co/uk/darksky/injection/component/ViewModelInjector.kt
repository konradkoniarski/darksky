package co.uk.darksky.injection.component

import co.uk.darksky.injection.module.NetworkModule
import co.uk.darksky.ui.forecast.ForecastViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(forecastViewModel: ForecastViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder

    }
}