package com.nomad.wfstats.models

import android.content.Context
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.nomad.wfstats.R

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

) /*{

    companion object {
        fun totalHeadshots(fullResponse: String): String {
            val reconHeadshots = fullResponse
                .substringAfter("<Sum> [class]Recon [mode]PVP [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()
            val medicHeadshots = fullResponse
                .substringAfter("<Sum> [class]Medic [mode]PVP [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()
            val riflemanHeadshots = fullResponse
                .substringAfter("<Sum> [class]Rifleman [mode]PVP [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()
            val engineerHeadshots = fullResponse
                .substringAfter("<Sum> [class]Engineer [mode]PVP [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()

            return (reconHeadshots + medicHeadshots + riflemanHeadshots + engineerHeadshots).toString()
        }

        fun listHeadshots(fullResponse: String, context: Context): List<Headshots> {
            val list = mutableListOf<Headshots>()
            val reconHeadshots = fullResponse
                .substringAfter("<Sum> [class]Recon [mode]PVP [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()
            list.add(Headshots(context.getString(R.string.recon), reconHeadshots))

            val medicHeadshots = fullResponse
                .substringAfter("<Sum> [class]Medic [mode]PVP [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()
            list.add(Headshots(context.getString(R.string.medic), medicHeadshots))

            val riflemanHeadshots = fullResponse
                .substringAfter("<Sum> [class]Rifleman [mode]PVP [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()
            list.add(Headshots(context.getString(R.string.rifleman), riflemanHeadshots))

            val engineerHeadshots = fullResponse
                .substringAfter("<Sum> [class]Engineer [mode]PVP [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()
            list.add(Headshots(context.getString(R.string.engineer), engineerHeadshots))
            return list
        }

        fun totalHeadshotsCoop(fullResponse: String): String {
            val reconHeadshots = fullResponse
                .substringAfter("<Sum> [class]Recon [mode]PVE [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()
            val medicHeadshots = fullResponse
                .substringAfter("<Sum> [class]Medic [mode]PVE [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()
            val riflemanHeadshots = fullResponse
                .substringAfter("<Sum> [class]Rifleman [mode]PVE [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()
            val engineerHeadshots = fullResponse
                .substringAfter("<Sum> [class]Engineer [mode]PVE [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()
            val heavyHeadshots = fullResponse
                .substringAfter("<Sum> [class]Heavy [mode]PVE [stat]player_headshots  = ", "0")
                .substringBefore("\n<Sum>").toInt()
            return (reconHeadshots + medicHeadshots + riflemanHeadshots + engineerHeadshots + heavyHeadshots).toString()
        }

        fun damageDealt(fullResponse: String): String =
            fullResponse.substringAfter("<Sum> [stat]player_damage  = ", "0")
                .substringBefore("\n<Sum>")

        fun coinsUsed(fullResponse: String): String =
            fullResponse.substringAfter("<Sum> [stat]player_resurrected_by_coin  = ", "0")
                .substringBefore("\n<Sum>")

        fun sessionLeft(fullResponse: String): String =
            fullResponse.substringAfter("<Sum> [mode]PVE [stat]player_sessions_left  = ", "0")
                .substringBefore("\n<Sum>")

        fun sessionKicked(fullResponse: String): String =
            fullResponse.substringAfter("<Sum> [mode]PVE [stat]player_sessions_kicked  = ", "0")
                .substringBefore("\n<Sum>")

        fun meleeKills(fullResponse: String): String =
            fullResponse.substringAfter("<Sum> [mode]PVE [stat]player_kills_melee  = ", "0")
                .substringBefore("\n<Sum>")

        fun playTimeCoop(fullResponse: String): String {
            val recon =
                fullResponse.substringAfter(
                    "<Sum> [class]Recon [mode]PVE [stat]player_playtime  = ",
                    "0"
                )
                    .substringBefore("\n<Sum>").toInt()
            val engineer =
                fullResponse.substringAfter(
                    "<Sum> [class]Engineer [mode]PVE [stat]player_playtime  = ",
                    "0"
                )
                    .substringBefore("\n<Sum>").toInt()
            val medic =
                fullResponse.substringAfter(
                    "<Sum> [class]Medic [mode]PVE [stat]player_playtime  = ",
                    "0"
                )
                    .substringBefore("\n<Sum>").toInt()
            val rifleman =
                fullResponse.substringAfter(
                    "<Sum> [class]Rifleman [mode]PVE [stat]player_playtime  = ",
                    "0"
                )
                    .substringBefore("\n<Sum>").toInt()
            val heavy =
                fullResponse.substringAfter(
                    "<Sum> [class]Heavy [mode]PVE [stat]player_playtime  = ",
                    "0"
                )
                    .substringBefore("\n<Sum>").toInt()

            val result = ((recon + engineer + medic + rifleman + heavy) / 60) / 60
            return result.toString()

        }
    }
}


















*/