package com.nomad.wfstats.negocio

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Player(
    /*  nickname: String,
      experience: Int,
      rankId: Int,
      clanId: Int,
      clanName: String,
      kill: Int,
      friendlyKills: Int,
      kills: Int,
      death: Int,
      kdPvp: Double,
      coopKill: Int,
      coopFriendlyKill: Int,
      coopKills: Int,
      coopDeath: Int,
      kdCoop: Double,
      playTime: Int,
      hourPlaytime: Int,
      minutePlaytime: Int,
      favoritePvp: Int,
      favoriteCoop: Int,
      coopWins: Int,
      pvpWins: Int,
      pvpLost: Int,
      coopLost: Int,
      winLosePvp: Int,
      winLoseCoop: Int,
      winLosePvpRatio: Double*/

    @SerializedName("user_id")
    @Expose
    var userId: String?,

    @SerializedName("nickname")
    @Expose
    var nickname: String?,

    @SerializedName("experience")
    @Expose
    var experience: Int?,

    @SerializedName("rank_id")
    @Expose
    var rankId: Int?,

    @SerializedName("clan_id")
    @Expose
    var clanId: Int?,

    @SerializedName("clan_name")
    @Expose
    var clanName: String?,

    @SerializedName("kill")
    @Expose
    var kill: Int?,

    @SerializedName("friendly_kills")
    @Expose
    var friendlyKills: Int?,

    @SerializedName("kills")
    @Expose
    var kills: Int?,

    @SerializedName("death")
    @Expose
    var death: Int?,

    @SerializedName("pvp")
    @Expose
    var pvp: Double?,

    @SerializedName("pve_kill")
    @Expose
    var pveKill: Int?,

    @SerializedName("pve_friendly_kills")
    @Expose
    var pveFriendlyKills: Int?,

    @SerializedName("pve_kills")
    @Expose
    var pveKills: Int?,

    @SerializedName("pve_death")
    @Expose
    var pveDeath: Int?,

    @SerializedName("pve")
    @Expose
    var pve: Double?,

    @SerializedName("playtime")
    @Expose
    var playtime: Int?,

    @SerializedName("playtime_h")
    @Expose
    var playtimeH: Int?,

    @SerializedName("playtime_m")
    @Expose
    var playtimeM: Int?,

    @SerializedName("favoritPVP")
    @Expose
    var favoritPVP: String?,

    @SerializedName("favoritPVE")
    @Expose
    var favoritPVE: String?,

    @SerializedName("pve_wins")
    @Expose
    var pveWins: Int?,

    @SerializedName("pvp_wins")
    @Expose
    var pvpWins: Int?,

    @SerializedName("pvp_lost")
    @Expose
    var pvpLost: Int?,

    @SerializedName("pve_lost")
    @Expose
    var pveLost: Int?,

    @SerializedName("pve_all")
    @Expose
    var pveAll: Int?,

    @SerializedName("pvp_all")
    @Expose
    var pvpAll: Int?,

    @SerializedName("pvpwl")
    @Expose
    var pvpwl: Double?,

    @SerializedName("full_response")
    @Expose
    var fullResponse: String?
)