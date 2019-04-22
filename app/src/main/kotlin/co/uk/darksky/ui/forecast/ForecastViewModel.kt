package co.uk.darksky.ui.forecast

import android.arch.lifecycle.MutableLiveData
import android.view.View
import co.uk.darksky.R
import co.uk.darksky.model.Forecast
import co.uk.darksky.network.ForecastApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Copyright (c) 2019 Mobile Vision Technologies LTD. All rights reserved.
 * Created on 4/22/19.
 */
class ForecastViewModel : co.uk.darksky.base.BaseViewModel() {
    @Inject
    lateinit var forecastApi: ForecastApi

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener {
        loadForecast()
    }
    val forecast: MutableLiveData<Forecast> = MutableLiveData()
    val longitude: MutableLiveData<String> = MutableLiveData();
    val latitude: MutableLiveData<String> = MutableLiveData();


    private lateinit var subscription: Disposable

    private fun loadForecast() {
        subscription = forecastApi.getForecast("360df1e319e95044fe7efd7c859c85b8", latitude.value.orEmpty(), longitude.value.orEmpty())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> onRetrieveForecastSuccess(result) },
                        { onRetrieveForecastError() }
                )
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun onRetrieveForecastSuccess(forecast: Forecast) {

        this.forecast.value = forecast;
    }

    private fun onRetrieveForecastError() {
        errorMessage.value = R.string.post_error
    }

    fun checkNewYorkWeather() {
        latitude.value = "40.7128";
        longitude.value = "-74.0060";

        loadForecast()
    }

    fun checkBarcelonaWeather() {
        latitude.value = "41.3851";
        longitude.value = "2.1734";

        loadForecast()
    }
}