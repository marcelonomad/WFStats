package com.nomad.wfstats.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Player(
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

) {

    companion object {
        fun totalHeadshots(fullResponse: String): String {
            val reconHeadshots = fullResponse
                .substringAfter("<Sum> [class]Recon [mode]PVP [stat]player_headshots  = ")
                .substringBefore("\n<Sum>").toInt()
            val medicHeadshots = fullResponse
                .substringAfter("<Sum> [class]Medic [mode]PVP [stat]player_headshots  = ")
                .substringBefore("\n<Sum>").toInt()
            val riflemanHeadshots = fullResponse
                .substringAfter("<Sum> [class]Rifleman [mode]PVP [stat]player_headshots  = ")
                .substringBefore("\n<Sum>").toInt()
            val engineerHeadshots = fullResponse
                .substringAfter("<Sum> [class]Engineer [mode]PVP [stat]player_headshots  = ")
                .substringBefore("\n<Sum>").toInt()

            return (reconHeadshots + medicHeadshots + riflemanHeadshots + engineerHeadshots).toString()
        }

        fun damageDealt(fullResponse: String): String =
            fullResponse.substringAfter("<Sum> [stat]player_damage  = ")
                .substringBefore("\n<Sum>")
    }
}

















