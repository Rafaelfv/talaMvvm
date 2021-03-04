package com.rafaelfv.improvingtest.service.repository

import com.rafaelfv.improvingtest.service.model.Data
import com.rafaelfv.improvingtest.service.model.Locales
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiData {

    @Headers("Accept: application/json", "Connection: close")
    @GET("locales.json")
    fun getLocales(): Single<Response<Locales>>


    @Headers("Accept: application/json", "Connection: close")
    @GET("testData.json")
    fun getData(): Single<Response<Data>>
}