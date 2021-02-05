package com.nomad.wfstats.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ClanMember(
    @SerializedName("nickname")
    @Expose
    var nickname: String?,
    @SerializedName("rank_id")
    @Expose
    var rank_id: String?,
    @SerializedName("clan_points")
    @Expose
    var clan_points: String?,
    @SerializedName("clan_role")
    @Expose
    var clan_role: String?
)
