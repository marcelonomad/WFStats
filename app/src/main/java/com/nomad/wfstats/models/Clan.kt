package com.nomad.wfstats.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Clan(
    @SerializedName("id")
    @Expose
    var id: String?,

    @SerializedName("name")
    @Expose
    var name: String?,

    @SerializedName("members")
    @Expose
    var members: List<ClanMember>
)

