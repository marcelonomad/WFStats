package com.nomad.wfstats.models

import java.io.Serializable

data class PlayerDetails(
    //region PVP
    var ammoRestored: Int,
    var climbAssists: Int,
    var climbCoop: Int,
    var pvpMelee: Int,
    var pvpReconPlaytime: Int,
    var pvpMedicPlaytime: Int,
    var pvpRiflemanPlaytime: Int,
    var pvpEngineerPlaytime: Int,
    var pvpHeavyPlaytime: Int,
    var repair: Int,
    var resurrectMade: Int,
    var pvpKicked: Int,
    var pvpLeft: Int,
    var engineerClaymore: Int,
    var heal: Int,
    var pvpReconHeadshots: Int,
    var pvpMedicHeadshots: Int,
    var pvpRiflemanHeadshots: Int,
    var pvpEngineerHeadshots: Int,
    var pvpDamageDealt: Int,
    //var pvpHeavyHeadshots: Int,
    //endregion

    //region Coop
    var pveReconPlaytime: Int,
    var pveMedicPlaytime: Int,
    var pveRiflemanPlaytime: Int,
    var pveEngineerPlaytime: Int,
    var pveHeavyPlaytime: Int,
    var pveReconHeadshots: Int,
    var pveMedicHeadshots: Int,
    var pveRiflemanHeadshots: Int,
    var pveEngineerHeadshots: Int,
    var pveHeavyHeadshots: Int,
    var coinsUsed: Int,
    var pveLeft: Int,
    var pveKicked: Int,
    var pveMelee: Int
    //endregion
) : Serializable {

    companion object {
        fun playerDetails(fullResponse: String): PlayerDetails {
            return PlayerDetails(
                fullResponse.substringAfter("<Sum> [stat]player_ammo_restored  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [stat]player_climb_assists  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [stat]player_climb_coops  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [mode]PVP [stat]player_kills_melee  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter(
                    "<Sum> [class]Recon [mode]PVP [stat]player_playtime  = ",
                    "0"
                )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter(
                    "<Sum> [class]Medic [mode]PVP [stat]player_playtime  = ",
                    "0"
                )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter(
                    "<Sum> [class]Rifleman [mode]PVP [stat]player_playtime  = ",
                    "0"
                )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter(
                    "<Sum> [class]Engineer [mode]PVP [stat]player_playtime  = ",
                    "0"
                )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter(
                    "<Sum> [class]Heavy [mode]PVP [stat]player_playtime  = ",
                    "0"
                )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [stat]player_repair  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [stat]player_resurrect_made  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [mode]PVP [stat]player_sessions_kicked  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [mode]PVP [stat]player_sessions_left  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter(
                    "<Sum> [class]Engineer [mode]PVP [stat]player_kills_claymore  = ",
                    "0"
                )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [stat]player_heal  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter("<Sum> [class]Recon [mode]PVP [stat]player_headshots  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter("<Sum> [class]Medic [mode]PVP [stat]player_headshots  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter(
                        "<Sum> [class]Rifleman [mode]PVP [stat]player_headshots  = ",
                        "0"
                    )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter(
                        "<Sum> [class]Engineer [mode]PVP [stat]player_headshots  = ",
                        "0"
                    )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [stat]player_damage  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter(
                        "<Sum> [class]Recon [mode]PVE [stat]player_playtime  = ",
                        "0"
                    )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter(
                        "<Sum> [class]Medic [mode]PVE [stat]player_playtime  = ",
                        "0"
                    )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter(
                        "<Sum> [class]Rifleman [mode]PVE [stat]player_playtime  = ",
                        "0"
                    )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter(
                        "<Sum> [class]Engineer [mode]PVE [stat]player_playtime  = ",
                        "0"
                    )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter(
                        "<Sum> [class]Heavy [mode]PVE [stat]player_playtime  = ",
                        "0"
                    )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter(
                        "<Sum> [class]Recon [mode]PVE [stat]player_headshots  = ",
                        "0"
                    )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter(
                        "<Sum> [class]Medic [mode]PVE [stat]player_headshots  = ",
                        "0"
                    )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter(
                        "<Sum> [class]Rifleman [mode]PVE [stat]player_headshots  = ",
                        "0"
                    )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter(
                        "<Sum> [class]Engineer [mode]PVE [stat]player_headshots  = ",
                        "0"
                    )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse
                    .substringAfter(
                        "<Sum> [class]Heavy [mode]PVE [stat]player_headshots  = ",
                        "0"
                    )
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [stat]player_resurrected_by_coin  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [mode]PVE [stat]player_sessions_left  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [mode]PVE [stat]player_sessions_kicked  = ", "0")
                    .substringBefore("\n<Sum>").toInt(),
                fullResponse.substringAfter("<Sum> [mode]PVE [stat]player_kills_melee  = ", "0")
                    .substringBefore("\n<Sum>").toInt()

            )


        }
    }

}