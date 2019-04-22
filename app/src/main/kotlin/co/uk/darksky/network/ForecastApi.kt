package co.uk.darksky.network

import co.uk.darksky.model.Forecast
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Copyright (c) 2019 Mobile Vision Technologies LTD. All rights reserved.
 * Created on 4/22/19.
 */
interface ForecastApi {
    @GET("forecast/{apiKey}/{latitude},{longitude}?units=si")
    fun getForecast(
            @Path("apiKey") apiKey: String,
            @Path("latitude") latitude: String,
            @Path("longitude") longitude: String): Single<Forecast>;
}