package com.arnoract.piggiplan

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.arnoract.piggiplan.di.AppComponent
import com.arnoract.piggiplan.ui.di.UiComponent
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.android.libraries.places.api.Places
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Places.initialize(applicationContext, getString(R.string.google_maps_key), Locale.US)
        startKoin {
            androidContext(this@MyApplication)
        }
        AppComponent.init()
        UiComponent.init()
        Fresco.initialize(this)
    }
}