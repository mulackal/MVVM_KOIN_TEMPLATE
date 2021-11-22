package com.mvvm.mykotlinkoin_template.ui.main.view

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.mvvm.mykotlinkoin_template.BaseActivity
import com.mvvm.mykotlinkoin_template.MainActivity
import com.mvvm.mykotlinkoin_template.R

import com.mvvm.mykotlinkoin_template.ui.main.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity() {

    private val splashViewModel : SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel!!.splashTimeOut()

    }



    override fun onDestroy() {
        splashViewModel!!.setRemoveHandler()
        this.finish()
        super.onDestroy()
    }
}