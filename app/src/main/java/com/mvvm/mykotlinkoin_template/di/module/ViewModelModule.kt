package com.mvvm.mykotlinkoin_template.di.module

import com.mvvm.mykotlinkoin_template.ui.main.viewmodel.MainViewModel
import com.mvvm.mykotlinkoin_template.ui.main.viewmodel.PagingViewModel
import com.mvvm.mykotlinkoin_template.ui.main.viewmodel.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get(),get(),get()) }
    viewModel { SplashViewModel(androidContext(),get()) }
    viewModel { PagingViewModel(get(),get()) }

}