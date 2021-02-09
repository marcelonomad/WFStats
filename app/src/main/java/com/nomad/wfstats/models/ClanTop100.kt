package com.nomad.wfstats.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class ClanTop100(
    @SerializedName("clan")
    @Expose
    private val clan: String? = null,

    @SerializedName("clan_leader")
    @Expose
    private val clanLeader: String? = null,

    @SerializedName("members")
    @Expose
    private val members: String? = null,

    @SerializedName("points")
    @Expose
    private val points: String? = null,

    @SerializedName("rank")
    @Expose
    private val rank: String? = null

)