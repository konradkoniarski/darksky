package co.uk.darksky.ui.forecast

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import co.uk.darksky.R
import kotlinx.android.synthetic.main.activity_forecast.*

/**
 * Copyright (c) 2019 Mobile Vision Technologies LTD. All rights reserved.
 * Created on 4/22/19.
 */
class ForecastActivity : AppCompatActivity() {
    private lateinit var binding: co.uk.darksky.databinding.ActivityForecastBinding
    private lateinit var viewModel: ForecastViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forecast)

        viewModel = ViewModelProviders.of(this, co.uk.darksky.injection.ViewModelFactory(this)).get(ForecastViewModel::class.java);
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel


        val b = intent.extras
        var value = "" // or other values
        if (b != null)
            value = b.getString("Version", "unknown")
        when (value) {
            "free" -> {
                btnBarcelona.visibility = View.GONE
            }
            "paid" -> {
                // do something else
            }
        }

        binding.setLifecycleOwner(this)
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}