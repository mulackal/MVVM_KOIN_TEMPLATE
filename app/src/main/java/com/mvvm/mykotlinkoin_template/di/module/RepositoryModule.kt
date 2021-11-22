package com.mvvm.mykotlinkoin_template.di.module

import com.mvvm.mykotlinkoin_template.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single { MainRepository(get()) }
}