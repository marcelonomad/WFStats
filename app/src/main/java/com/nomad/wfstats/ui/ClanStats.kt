package com.nomad.wfstats.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nomad.wfstats.R
import com.nomad.wfstats.models.Clan
import com.nomad.wfstats.models.NetworkUtils
import com.nomad.wfstats.models.adapters.ClanMemberAdapter
import com.nomad.wfstats.models.adapters.Server
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
        txtClanName.setText(getClanName(this))
        crdFindClan.visibility = View.VISIBLE
        crdClan.visibility = View.GONE
        rcvMembers.visibility = View.GONE
        pgbClan.visibility = View.GONE

        val servers = getServer()
        val spnAdapter = Server(this, servers)
        spnServerClan.adapter = spnAdapter

        btnFindClan.setOnClickListener {
            if (txtClanName.text.isNotEmpty()) {
                saveClanName(this, txtClanName.text.toString())
                pgbClan.visibility = View.VISIBLE
                crdFindClan.visibility = View.INVISIBLE
                getClan()
            } else
                Toast.makeText(this, getString(R.string.hint_type_clan), Toast.LENGTH_LONG).show()
        }

    }

    fun getClan() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance()
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getClanMembers(
            (spnServerClan.selectedItem as ServerCode).code,
            txtClanName.text.toString()
        )

        callback.enqueue(object : Callback<Clan> {
            override fun onFailure(call: Call<Clan>, t: Throwable) {
                Toast.makeText(baseContext, R.string.error_finding_clan, Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<Clan>, response: Response<Clan>) {
                if (response.body() != null) {
                    lblClanName.text = response.body()?.name

                    val lm = LinearLayoutManager(this@ClanStats)
                    rcvMembers.layoutManager = lm
                    if (response.body()?.members!!.isNotEmpty()) {
                        val adapter =
                            ClanMemberAdapter(response.body()?.members!!.toList(), this@ClanStats)
                        rcvMembers.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    crdFindClan.visibility = View.GONE
                    crdClan.visibility = View.VISIBLE
                    rcvMembers.visibility = View.VISIBLE
                    pgbClan.visibility = View.GONE
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

    fun getClanName(context: Context): String {
        val clanName = getString(R.string.clanname)
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(clanName, Context.MODE_PRIVATE)
        return sharedPref.getString(clanName, "").toString()
    }

    fun saveClanName(context: Context, name: String) {
        val clanName = getString(R.string.clanname)
        val sharedPref: SharedPreferences =
            context.getSharedPreferences(clanName, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(clanName, name)
        editor.commit()
    }

}





























