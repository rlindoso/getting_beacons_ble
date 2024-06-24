package com.rlindoso.gettingbeacons.service.repository.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {

        private lateinit var retrofit: Retrofit

        private fun getRetrofitInstance(ipBackend: String?): Retrofit {
            var baseurl = "http://${ipBackend}/"
            if (ipBackend.isNullOrBlank()) {
                baseurl = "http://localhost/"
            }
            val httpClient = OkHttpClient.Builder()
            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseurl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

        fun <T> createService(serviceClass: Class<T>, ipBackend: String?): T {
            return getRetrofitInstance(ipBackend).create(serviceClass)
        }

    }

}