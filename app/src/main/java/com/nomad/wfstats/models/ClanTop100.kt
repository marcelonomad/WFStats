package com.nomad.wfstats.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class ClanTop100(
    @SerializedName("clan")
    @Expose
    val clan: String? = null,

    @SerializedName("clan_leader")
    @Expose
    val clanLeader: String? = null,

    @SerializedName("members")
    @Expose
    val members: String? = null,

    @SerializedName("points")
    @Expose
    val points: String? = null,

    @SerializedName("rank")
    @Expose
    val rank: String? = null

)