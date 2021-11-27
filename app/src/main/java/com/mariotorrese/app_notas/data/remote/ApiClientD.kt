package com.mariotorrese.app_notas.data.remote

import com.google.gson.GsonBuilder
import com.mariotorrese.app_notas.app.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientD {
    val service by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.UrlBase)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(ApiService::class.java)

    }
}