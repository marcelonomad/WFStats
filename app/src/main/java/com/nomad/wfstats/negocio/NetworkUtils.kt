package com.nomad.wfstats.negocio

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkUtils {
    companion object {
        fun getRetrofitInstance(): Retrofit {
            val url = "http://api.wf.my.com/"
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
