package com.nomad.wfstats.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nomad.wfstats.R
import com.nomad.wfstats.models.ClanTop100
import com.nomad.wfstats.models.NetworkUtils
import com.nomad.wfstats.models.adapters.ClanMemberAdapter
import com.nomad.wfstats.models.adapters.ClanTop100Adapter
import com.nomad.wfstats.models.adapters.Server
import com.nomad.wfstats.models.adapters.ServerCode
import com.nomad.wfstats.models.interfaces.Endpoint
import kotlinx.android.synthetic.main.activity_clan_stats.*
import kotlinx.android.synthetic.main.activity_clan_top100.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClanTop100 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clan_top100)

        crdFindClanTop100.visibility = View.VISIBLE
        crdClanTop100.visibility = View.INVISIBLE
        rcvClanTop100.visibility = View.INVISIBLE
        pgbClanTop100.visibility = View.INVISIBLE

        val servers = getServer()
        val spnAdapter = Server(this, servers)
        spnClanTop100.adapter = spnAdapter

        btnFindClanTop100.setOnClickListener {
            pgbClanTop100.visibility = View.VISIBLE
            crdFindClanTop100.visibility = View.INVISIBLE
            getTop100Clans()

        }


    }

    private fun getTop100Clans() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance()
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getClanTop100((spnClanTop100.selectedItem as ServerCode).code)

        callback.enqueue(object : Callback<List<ClanTop100>> {
            override fun onFailure(call: Call<List<ClanTop100>>, t: Throwable) {
                Toast.makeText(baseContext, getString(R.string.error_internet), Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(
                call: Call<List<ClanTop100>>,
                response: Response<List<ClanTop100>>
            ) {
                if (response.body() != null) {
                    val lm = LinearLayoutManager(this@ClanTop100)

                    rcvClanTop100.layoutManager = lm
                    val adapter =
                        ClanTop100Adapter(
                            response.body()!!.toList(),
                            (spnClanTop100.selectedItem as ServerCode),
                            this@ClanTop100
                        )
                    rcvClanTop100.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
                crdFindClanTop100.visibility = View.INVISIBLE
                crdClanTop100.visibility = View.VISIBLE
                rcvClanTop100.visibility = View.VISIBLE
                pgbClanTop100.visibility = View.INVISIBLE
            }
        })

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

}













