package com.nomad.wfstats.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nomad.wfstats.R
import com.nomad.wfstats.ui.warbox.MainWarbox
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
        btn_Clan_Top_100.setOnClickListener {
            val i = Intent(this, ClanTop100::class.java)
            startActivity(i)
        }
        btn_Warbox.setOnClickListener {
            val i = Intent(this, MainWarbox::class.java)
            startActivity(i)
        }
    }
}