package co.uk.darksky.injection.repository

import co.uk.darksky.model.Forecast
import io.reactivex.Single

/**
 * Copyright (c) 2019 Mobile Vision Technologies LTD. All rights reserved.
 * Created on 4/22/19.
 */
interface ForecastRepository {
    fun getForecast(): Single<Forecast>
}