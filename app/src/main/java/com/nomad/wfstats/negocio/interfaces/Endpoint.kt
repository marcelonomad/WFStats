package com.nomad.wfstats.negocio.interfaces

import com.nomad.wfstats.negocio.Player
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("user/stat/")
    fun getPlayer(@Query("name") name: String, @Query("server") server: Int): Call<Player>
}