package com.mvvm.mykotlinkoin_template.di.module

import com.mvvm.mykotlinkoin_template.data.preferences.SharedPreferenceUtils

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val sessionModule = module {

        single { SharedPreferenceUtils(androidApplication()) }
}