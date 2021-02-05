package com.nomad.wfstats.ui

import android.content.Context
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
import com.nomad.wfstats.models.adapters.Server
import com.nomad.wfstats.models.adapters.ServerCode
import com.nomad.wfstats.models.interfaces.Endpoint
import com.nomad.wfstats.models.util.Formatacao
import kotlinx.android.synthetic.main.activity_player_stats.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerStats : AppCompatActivity() {
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
    }

    fun getPlayer() {
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

            override fun onResponse(call: Call<Player>, response: Response<Player>) {
                if (response.body() != null) {
                    lblPlayerNickname.text = response.body()?.nickname

                    lblExperience.text =
                        "Exp: ${Formatacao.formatarNumero(response.body()?.experience)}"
                    lblClan.text = response.body()?.clanName
                    lblPlayTime.text =
                        "${getString(R.string.playtime)}: ${response.body()?.playtimeH}:${response.body()?.playtimeM}h"

                    lblKills.text =
                        "${getString(R.string.kills)}: ${Formatacao.formatarNumero(response.body()?.kills)}"
                    lblDeath.text =
                        "${getString(R.string.deaths)}: ${Formatacao.formatarNumero(response.body()?.death)}"
                    lblFriendlyKills.text =
                        "${getString(R.string.friendlykills)}: ${Formatacao.formatarNumero(response.body()?.friendlyKills)}"
                    lblKD.text = "${getString(R.string.kd)}: ${response.body()?.pvp}"
                    lblPVPWins.text = "${getString(R.string.pvp_wins)}: ${Formatacao.formatarNumero( response.body()?.pvpWins)}"
                    lblPVPLoses.text =
                        "${getString(R.string.pvp_loses)}: ${Formatacao.formatarNumero( response.body()?.pvpLost)}"

                    lblHeadshots.text =
                        "${getString(R.string.headshots)}: ${Formatacao.formatarNumero(Player.totalHeadshots(response.body()?.fullResponse.toString()).toInt())}"
                    lblDamageDealt.text =
                        "${getString(R.string.damage_dealt)}: ${Formatacao.formatarNumero(Player.damageDealt(response.body()?.fullResponse.toString()).toInt())}"

                    when (response.body()?.favoritPVP) {
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

    fun getServer(): List<ServerCode> {
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

    fun getPlayerName(context: Context): String {
        val playerName = getString(R.string.playername)
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(playerName, Context.MODE_PRIVATE)
        return sharedPref.getString(playerName, "").toString()
    }

    fun savePlayerName(context: Context, name: String) {
        val playerName = getString(R.string.playername)
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(playerName, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(playerName, name)
        editor.commit()
    }
}