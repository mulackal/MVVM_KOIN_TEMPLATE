package com.mvvm.mykotlinkoin_template

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.mvvm.mykotlinkoin_template.di.module.*
import com.mvvm.mykotlinkoin_template.utils.customviews.FontsOverride
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {

    private var currentActivity: Activity? = null

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/NunitoSans-Regular.ttf")


        startKoin {
            androidContext(this@BaseApplication)
            androidLogger(Level.ERROR)
            modules(listOf(appModule, repoModule, sessionModule, viewModelModule, persistenceModule))
        }


    }

    fun getCurrentActivity(): Activity? { return currentActivity }

    fun setCurrentActivity(mCurrentact: Activity) { this.currentActivity = mCurrentact }

    companion object {
        private var uniqInstance: BaseApplication? = null
        val instance: BaseApplication
            @Synchronized get() {
                if (uniqInstance == null) {
                    uniqInstance = BaseApplication()
                }
                return uniqInstance as BaseApplication
            }
    }
}
