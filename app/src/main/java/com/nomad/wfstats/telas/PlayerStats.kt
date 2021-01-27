package com.nomad.wfstats.telas

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nomad.wfstats.R
import com.nomad.wfstats.negocio.NetworkUtils
import com.nomad.wfstats.negocio.Player
import com.nomad.wfstats.negocio.Server
import com.nomad.wfstats.negocio.ServerCode
import com.nomad.wfstats.negocio.interfaces.Endpoint
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

        val servers = getServer()

        val spnAdapter = Server(this, servers)
        spnServer.adapter = spnAdapter

        btnFindPlayer.setOnClickListener {
            pgbPlayer.visibility = View.VISIBLE
            crdPlayer.visibility = View.GONE
            getPlayer()
        }
    }

    fun getPlayer() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance()
        var a = txtPlayerName.text.toString()
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPlayer(txtPlayerName.text.toString(), 1)

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
                    lblExperience.text = response.body()?.experience.toString()
                    lblClan.text = response.body()?.clanName
                    lblPlayTime.text =
                        "${getString(R.string.playtime)}: ${response.body()?.playtimeH}:${response.body()?.playtimeM}h"
                    lblKills.text = "${getString(R.string.kills)}: ${response.body()?.kills}"
                    lblDeath.text = getString(R.string.deaths) + ": " + response.body()?.death
                    lblFriendlyKills.text =
                        "${getString(R.string.friendlykills)}: ${response.body()?.friendlyKills}"
                    lblKD.text = "${getString(R.string.kd)}: ${response.body()?.pvp}"
                    lblPVPWins.text = "${getString(R.string.pvp_wins)}: ${response.body()?.pvpWins}"
                    lblPVPLoses.text =
                        "${getString(R.string.pvp_loses)}: ${response.body()?.pvpLost}"
                    pgbPlayer.visibility = View.GONE
                    crdPlayer.visibility = View.GONE
                    scvPlayer.visibility = View.VISIBLE
                }

            }
        })

    }

    fun getServer(): Array<ServerCode> {
        return ServerCode.values().asList().toTypedArray()
    }
}