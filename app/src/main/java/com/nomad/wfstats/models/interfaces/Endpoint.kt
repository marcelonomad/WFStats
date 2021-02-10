package com.nomad.wfstats.models.interfaces

import com.nomad.wfstats.models.Clan
import com.nomad.wfstats.models.ClanTop100
import com.nomad.wfstats.models.Player
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("user/stat/")
    fun getPlayer(@Query("name") name: String, @Query("server") server: Int): Call<Player>

    @GET("clan/members/")
    fun getClanMembers(@Query("server") server: Int, @Query("clan") clan: String): Call<Clan>

    @GET("rating/clan/")
    fun getClanTop100(@Query("server") server: Int): Call<List<ClanTop100>>


}