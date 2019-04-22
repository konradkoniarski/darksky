package co.uk.darksky.ui.landing

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import co.uk.darksky.ui.forecast.ForecastActivity


/**
 * Copyright (c) 2019 Mobile Vision Technologies LTD. All rights reserved.
 * Created on 4/22/19.
 */
class LandingViewModel(var activity: Activity) : co.uk.darksky.base.BaseViewModel() {


    fun onFreeVersionSelected() {
        runForcastActivity("free")
    }

    fun onPaidVersionSelected() {
        runForcastActivity("paid")
    }

    private fun runForcastActivity(type: String) {
        val bundle = Bundle()
        bundle.putString("Version", type)
        val intent = Intent(activity, ForecastActivity::class.java)
        intent.putExtras(bundle)
        activity.startActivity(intent)
    }
}