package co.uk.darksky.ui.landing

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.uk.darksky.R

/**
 * Copyright (c) 2019 Mobile Vision Technologies LTD. All rights reserved.
 * Created on 4/22/19.
 */
class LandingActivity : AppCompatActivity() {
    private lateinit var binding: co.uk.darksky.databinding.ActivityLandingBinding
    private lateinit var viewModel: LandingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_landing)

        viewModel = ViewModelProviders.of(this, co.uk.darksky.injection.ViewModelFactory(this)).get(LandingViewModel::class.java);
        binding.viewModel = viewModel
    }
}