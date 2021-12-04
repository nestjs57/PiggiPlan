package com.arnoract.piggiplan.core

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder(
    private val client: OkHttpClient,
    private val gson: Gson
) {
    fun build(): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://scanji.xyz/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}
