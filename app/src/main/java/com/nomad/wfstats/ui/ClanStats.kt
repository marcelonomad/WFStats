package com.nomad.wfstats.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nomad.wfstats.R
import com.nomad.wfstats.models.Clan
import com.nomad.wfstats.models.NetworkUtils
import com.nomad.wfstats.models.Player
import com.nomad.wfstats.models.adapters.ClanMemberAdapter
import com.nomad.wfstats.models.adapters.ServerCode
import com.nomad.wfstats.models.interfaces.Endpoint
import kotlinx.android.synthetic.main.activity_clan_stats.*
import kotlinx.android.synthetic.main.activity_player_stats.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClanStats : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clan_stats)
        getClan()
    }

    fun getClan() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance()
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getClanMembers(1, "BLACK-GOLD")

        callback.enqueue(object : Callback<Clan> {
            override fun onFailure(call: Call<Clan>, t: Throwable) {
                Toast.makeText(baseContext, R.string.error_finding_player, Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<Clan>, response: Response<Clan>) {
                if (response.body() != null) {
                    lblClanName.text = response.body()?.name

                    val lm = LinearLayoutManager(this@ClanStats)
                    rcvMembers.layoutManager = lm

                    val adapter = ClanMemberAdapter(response.body()?.members!!.toList(), this@ClanStats)
                    rcvMembers.adapter = adapter
                    adapter.notifyDataSetChanged()
                    response.body()?.members

                }
            }
        })
    }


}