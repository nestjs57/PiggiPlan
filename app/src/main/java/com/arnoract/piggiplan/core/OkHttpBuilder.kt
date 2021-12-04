package com.arnoract.piggiplan.core

import android.content.Context
import com.arnoract.piggiplan.core.isDebug
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpBuilder(
    private val context: Context
) {
    fun build(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        val chuckInterceptor = ChuckInterceptor(context)
        chuckInterceptor.showNotification(context.isDebug())
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(chuckInterceptor)
            .build()
    }
}
