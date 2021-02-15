package com.nomad.wfstats.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nomad.wfstats.R
import com.nomad.wfstats.models.NetworkUtils
import com.nomad.wfstats.models.Player
import com.nomad.wfstats.models.PlayerDetails
import com.nomad.wfstats.models.adapters.Server
import com.nomad.wfstats.models.adapters.ServerCode
import com.nomad.wfstats.models.interfaces.Endpoint
import com.nomad.wfstats.models.util.Formatter
import kotlinx.android.synthetic.main.activity_player_stats.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class PlayerStats : AppCompatActivity() {
    private lateinit var playerDetails: PlayerDetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_stats)
        crdPlayer.visibility = View.VISIBLE
        scvPlayer.visibility = View.GONE
        pgbPlayer.visibility = View.GONE
        txtPlayerName.setText(getPlayerName(this))

        val servers = getServer()

        val spnAdapter = Server(this, servers)
        spnServer.adapter = spnAdapter

        btnFindPlayer.setOnClickListener {
            if (txtPlayerName.text.isNotEmpty()) {
                savePlayerName(this, txtPlayerName.text.toString())
                pgbPlayer.visibility = View.VISIBLE
                crdPlayer.visibility = View.GONE
                getPlayer()
            } else
                Toast.makeText(this, getString(R.string.hint_type_player), Toast.LENGTH_LONG).show()
        }
        btnDetails.setOnClickListener {
            val i = Intent(this, PlayerDetailsStats::class.java)
            i.putExtra("playerDetails", playerDetails as Serializable)
            startActivity(i)
        }
    }

    private fun getPlayer() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance()
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPlayer(
            txtPlayerName.text.toString(),
            (spnServer.selectedItem as ServerCode).code
        )

        callback.enqueue(object : Callback<Player> {
            override fun onFailure(call: Call<Player>, t: Throwable) {
                pgbPlayer.visibility = View.GONE
                crdPlayer.visibility = View.VISIBLE
                Toast.makeText(baseContext, R.string.error_finding_player, Toast.LENGTH_SHORT)
                    .show()
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Player>, response: Response<Player>) {
                if (response.body() != null) {
                    lblPlayerNickname.text = response.body()?.nickname

                    playerDetails = PlayerDetails.playerDetails(response.body().toString())

                    //region PVP

                    lblExperience.text =
                        "Exp: ${Formatter.thousandFormat(response.body()?.experience)}"
                    lblClan.text = response.body()?.clanName
                    lblPlayTime.text =
                        "${getString(R.string.playtime)}: ${response.body()?.playtimeH}:${response.body()?.playtimeM}h"

                    lblKills.text =
                        "${getString(R.string.kills)}: ${Formatter.thousandFormat(response.body()?.kills)}"
                    lblDeath.text =
                        "${getString(R.string.deaths)}: ${Formatter.thousandFormat(response.body()?.death)}"
                    lblFriendlyKills.text =
                        "${getString(R.string.friendlykills)}: ${Formatter.thousandFormat(response.body()?.friendlyKills)}"
                    lblKD.text = "${getString(R.string.kd)}: ${response.body()?.pvp}"
                    lblPVPWins.text =
                        "${getString(R.string.pvp_wins)}: ${Formatter.thousandFormat(response.body()?.pvpWins)}"
                    lblPVPLoses.text =
                        "${getString(R.string.pvp_loses)}: ${Formatter.thousandFormat(response.body()?.pvpLost)}"

                    lblHeadshots.text =
                        "${getString(R.string.headshots)}: ${Formatter.thousandFormat(
                            playerDetails.pvpEngineerHeadshots +
                                    playerDetails.pvpMedicHeadshots +
                                    playerDetails.pvpReconHeadshots +
                                    playerDetails.pvpRiflemanHeadshots
                        )}"
                    lblDamageDealt.text =
                        "${getString(R.string.damage_dealt)}: ${Formatter.thousandFormat(
                            playerDetails.pvpDamageDealt
                        )}"
//endregion


                    //region COOP

                    lblKillsCoop.text =
                        "${getString(R.string.kills)}: ${Formatter.thousandFormat(response.body()?.pveKills)}"
                    lblDeathCoop.text =
                        "${getString(R.string.deaths)}: ${Formatter.thousandFormat(response.body()?.pveDeath)}"
                    lblFriendlyKillsCoop.text =
                        "${getString(R.string.friendlykills)}: ${Formatter.thousandFormat(response.body()?.pveFriendlyKills)}"
                    lblCoinsUsed.text =
                        "${getString(R.string.coins_used)}: ${Formatter.thousandFormat(
                            playerDetails.coinsUsed
                        )}"
                    lblHeadshotCoop.text =
                        "${getString(R.string.headshots)}: ${Formatter.thousandFormat(
                            playerDetails.pveEngineerHeadshots +
                                    playerDetails.pveHeavyHeadshots +
                                    playerDetails.pveMedicHeadshots +
                                    playerDetails.pveReconHeadshots +
                                    playerDetails.pveRiflemanHeadshots
                        )}"
                    lblSessionLeft.text =
                        "${getString(R.string.session_left)}: ${Formatter.thousandFormat(
                            playerDetails.pveLeft
                        )}"
                    lblSessionKicked.text =
                        "${getString(R.string.session_kicked)}: ${Formatter.thousandFormat(
                            playerDetails.pveKicked
                        )}"
                    lblMeleeKills.text =
                        "${getString(R.string.melee_kills)}: ${Formatter.thousandFormat(
                            playerDetails.pveMelee
                        )}"


                    //endregion

                    setImageClass(response.body()?.favoritPVP, response.body()?.favoritPVE)
                    setRankClass(response.body()?.rankId)

                    pgbPlayer.visibility = View.GONE
                    crdPlayer.visibility = View.GONE
                    scvPlayer.visibility = View.VISIBLE

                } else {
                    pgbPlayer.visibility = View.GONE
                    crdPlayer.visibility = View.VISIBLE
                    Toast.makeText(baseContext, R.string.error_finding_player, Toast.LENGTH_LONG)
                        .show()
                }
            }
        })

    }

    private fun setImageClass(favoritPVP: String?, favoritPVE: String?) {
        when (favoritPVP) {
            "Engineer" -> Glide.with(this@PlayerStats)
                .apply { RequestOptions.overrideOf(64, 64).fitCenter() }
                .load("https://i.imgur.com/4j8CmBK.png").into(imgFavoriteClass)
            "Medic" -> Glide.with(this@PlayerStats)
                .apply { RequestOptions.overrideOf(64, 64) }
                .load("https://i.imgur.com/twPvpDl.png").into(imgFavoriteClass)
            "Rifleman" -> Glide.with(this@PlayerStats)
                .apply { RequestOptions.overrideOf(64, 64) }
                .load("https://i.imgur.com/NsBcokU.png").into(imgFavoriteClass)
            "Recon" -> Glide.with(this@PlayerStats)
                .apply { RequestOptions.overrideOf(64, 64) }
                .load("https://i.imgur.com/XfoLrqj.png").into(imgFavoriteClass)
            "Heavy" -> Glide.with(this@PlayerStats)
                .apply { RequestOptions.overrideOf(64, 64) }
                .load("https://i.imgur.com/NzjfmeD.png").into(imgFavoriteClass)
        }
        when (favoritPVE) {
            "Engineer" -> Glide.with(this@PlayerStats)
                .apply { RequestOptions.overrideOf(64, 64).fitCenter() }
                .load("https://i.imgur.com/4j8CmBK.png").into(imgFavoriteClassCoop)
            "Medic" -> Glide.with(this@PlayerStats)
                .apply { RequestOptions.overrideOf(64, 64) }
                .load("https://i.imgur.com/twPvpDl.png").into(imgFavoriteClassCoop)
            "Rifleman" -> Glide.with(this@PlayerStats)
                .apply { RequestOptions.overrideOf(64, 64) }
                .load("https://i.imgur.com/NsBcokU.png").into(imgFavoriteClassCoop)
            "Recon" -> Glide.with(this@PlayerStats)
                .apply { RequestOptions.overrideOf(64, 64) }
                .load("https://i.imgur.com/XfoLrqj.png").into(imgFavoriteClassCoop)
            "Heavy" -> Glide.with(this@PlayerStats)
                .apply { RequestOptions.overrideOf(64, 64) }
                .load("https://i.imgur.com/NzjfmeD.png").into(imgFavoriteClassCoop)
        }
    }

    private fun setRankClass(rankId: Int?) {
        var rankURL = "https://i.imgur.com/pYdSsjp.png"
        when (rankId) {
            1 -> rankURL = "https://i.imgur.com/pYdSsjp.png"
            2 -> rankURL = "https://i.imgur.com/p167VgK.png"
            3 -> rankURL = "https://i.imgur.com/sk7qslH.png"
            4 -> rankURL = "https://i.imgur.com/0t6l5nf.png"
            5 -> rankURL = "https://i.imgur.com/9py1Rhh.png"
            6 -> rankURL = "https://i.imgur.com/dCPtH2o.png"
            7 -> rankURL = "https://i.imgur.com/dpJmrAC.png"
            8 -> rankURL = "https://i.imgur.com/R7EQAyg.png"
            9 -> rankURL = "https://i.imgur.com/9yAYLDt.png"
            10 -> rankURL = "https://i.imgur.com/rxNw7nf.png"
            11 -> rankURL = "https://i.imgur.com/dj0iwGy.png"
            12 -> rankURL = "https://i.imgur.com/ynua1vr.png"
            13 -> rankURL = "https://i.imgur.com/myNeelb.png"
            14 -> rankURL = "https://i.imgur.com/U0U98Xj.png"
            15 -> rankURL = "https://i.imgur.com/KUw7xuV.png"
            16 -> rankURL = "https://i.imgur.com/oQ9LFnU.png"
            17 -> rankURL = "https://i.imgur.com/6pVhfvh.png"
            18 -> rankURL = "https://i.imgur.com/3PEepw8.png"
            19 -> rankURL = "https://i.imgur.com/irAn9uA.png"
            20 -> rankURL = "https://i.imgur.com/AQ9bdZw.png"
            21 -> rankURL = "https://i.imgur.com/UpP0fv6.png"
            22 -> rankURL = "https://i.imgur.com/y6KAEpi.png"
            23 -> rankURL = "https://i.imgur.com/9brsC5A.png"
            24 -> rankURL = "https://i.imgur.com/3R4JjE8.png"
            25 -> rankURL = "https://i.imgur.com/P9DVoYT.png"
            26 -> rankURL = "https://i.imgur.com/SaMU3xW.png"
            27 -> rankURL = "https://i.imgur.com/jwVxTxy.png"
            28 -> rankURL = "https://i.imgur.com/NimZVdH.png"
            29 -> rankURL = "https://i.imgur.com/hC8zA1V.png"
            30 -> rankURL = "https://i.imgur.com/aymY8W0.png"
            31 -> rankURL = "https://i.imgur.com/52ugyqZ.png"
            32 -> rankURL = "https://i.imgur.com/xWyhLfP.png"
            33 -> rankURL = "https://i.imgur.com/UTUF7XI.png"
            34 -> rankURL = "https://i.imgur.com/zk9ZVNN.png"
            35 -> rankURL = "https://i.imgur.com/FLxrW7b.png"
            36 -> rankURL = "https://i.imgur.com/0cA1AyI.png"
            37 -> rankURL = "https://i.imgur.com/XajzHjn.png"
            38 -> rankURL = "https://i.imgur.com/CsTjliv.png"
            39 -> rankURL = "https://i.imgur.com/Rsdq8rK.png"
            40 -> rankURL = "https://i.imgur.com/h2YKidO.png"
            41 -> rankURL = "https://i.imgur.com/FWQpw1P.png"
            42 -> rankURL = "https://i.imgur.com/QNxq85X.png"
            43 -> rankURL = "https://i.imgur.com/8sJPCXu.png"
            44 -> rankURL = "https://i.imgur.com/63dEabK.png"
            45 -> rankURL = "https://i.imgur.com/4CAV6iF.png"
            46 -> rankURL = "https://i.imgur.com/IEYCZdO.png"
            47 -> rankURL = "https://i.imgur.com/IW74S7Z.png"
            48 -> rankURL = "https://i.imgur.com/hdva41F.png"
            49 -> rankURL = "https://i.imgur.com/uyRga5u.png"
            50 -> rankURL = "https://i.imgur.com/havuCRB.png"
            51 -> rankURL = "https://i.imgur.com/ZLpioYy.png"
            52 -> rankURL = "https://i.imgur.com/d7IkKua.png"
            53 -> rankURL = "https://i.imgur.com/5KxuRBo.png"
            54 -> rankURL = "https://i.imgur.com/lZzpiEQ.png"
            55 -> rankURL = "https://i.imgur.com/YxTdHLg.png"
            56 -> rankURL = "https://i.imgur.com/8iQIGMJ.png"
            57 -> rankURL = "https://i.imgur.com/lexrxsC.png"
            58 -> rankURL = "https://i.imgur.com/MXmiiyY.png"
            59 -> rankURL = "https://i.imgur.com/cEaPZbz.png"
            60 -> rankURL = "https://i.imgur.com/Q0zB4mH.png"
            61 -> rankURL = "https://i.imgur.com/wuudiRx.png"
            62 -> rankURL = "https://i.imgur.com/GluTXfB.png"
            63 -> rankURL = "https://i.imgur.com/ZfzlpUQ.png"
            64 -> rankURL = "https://i.imgur.com/lBROIcY.png"
            65 -> rankURL = "https://i.imgur.com/pbp6kKA.png"
            66 -> rankURL = "https://i.imgur.com/Ru8eNHh.png"
            67 -> rankURL = "https://i.imgur.com/rExJ9rr.png"
            68 -> rankURL = "https://i.imgur.com/uGaQ1Db.png"
            69 -> rankURL = "https://i.imgur.com/NwmNh82.png"
            70 -> rankURL = "https://i.imgur.com/rr6Gmd3.png"
            71 -> rankURL = "https://i.imgur.com/xkAcZxs.png"
            72 -> rankURL = "https://i.imgur.com/22rWFYc.png"
            73 -> rankURL = "https://i.imgur.com/FRs27Ho.png"
            74 -> rankURL = "https://i.imgur.com/aYYyx7M.png"
            75 -> rankURL = "https://i.imgur.com/5JO7IcY.png"
            76 -> rankURL = "https://i.imgur.com/WjcH07r.png"
            77 -> rankURL = "https://i.imgur.com/8rusUTA.png"
            78 -> rankURL = "https://i.imgur.com/yYY8Ljb.png"
            79 -> rankURL = "https://i.imgur.com/LrOQjDc.png"
            80 -> rankURL = "https://i.imgur.com/VHcWVXZ.png"
            81 -> rankURL = "https://i.imgur.com/Bl0jBTc.png"
            82 -> rankURL = "https://i.imgur.com/6KZuG8k.png"
            83 -> rankURL = "https://i.imgur.com/l8naErp.png"
            84 -> rankURL = "https://i.imgur.com/0ttcGHf.png"
            85 -> rankURL = "https://i.imgur.com/ePBRfWm.png"
            86 -> rankURL = "https://i.imgur.com/y57pjrh.png"
            87 -> rankURL = "https://i.imgur.com/jLobgqb.png"
            88 -> rankURL = "https://i.imgur.com/q24gWDM.png"
            89 -> rankURL = "https://i.imgur.com/U5wWfm4.png"
            90 -> rankURL = "https://i.imgur.com/diuag5r.png"
        }
        Glide.with(this@PlayerStats)
            .load(rankURL)
            .into(imgRank)
    }

    private fun getServer(): List<ServerCode> {
        val list = mutableListOf<ServerCode>()
        list.add(
            ServerCode(
                1,
                "EU",
                "https://i.imgur.com/Gi12J2d.png"
            )
        )
        list.add(
            ServerCode(
                2,
                "NA",
                "https://i.imgur.com/ncU8VFo.png"
            )
        )
        return list

    }

    private fun getPlayerName(context: Context): String {
        val playerName = getString(R.string.playername)
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(playerName, Context.MODE_PRIVATE)
        return sharedPref.getString(playerName, "").toString()
    }

    private fun savePlayerName(context: Context, name: String) {
        val playerName = getString(R.string.playername)
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(playerName, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(playerName, name)
        editor.commit()
    }
}
























