package com.nomad.wfstats.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nomad.wfstats.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_Player_Stats.setOnClickListener {
            val i = Intent(this, PlayerStats::class.java)
            startActivity(i)
        }
        btn_Clan_Stats.setOnClickListener {
            val i = Intent(this, ClanStats::class.java)
            startActivity(i)
        }
    }
}