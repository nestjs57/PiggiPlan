package com.arnoract.piggiplan

import android.app.Application
import com.arnoract.piggiplan.di.AppComponent
import com.arnoract.piggiplan.ui.di.UiComponent
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
        }
        AppComponent.init()
        UiComponent.init()
        Fresco.initialize(this)
    }
}