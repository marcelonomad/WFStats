package com.nomad.wfstats.models.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.nomad.wfstats.R
import com.nomad.wfstats.models.ClanTop100
import com.nomad.wfstats.models.util.Formatter
import com.nomad.wfstats.ui.ClanStats
import com.nomad.wfstats.ui.PlayerStats
import kotlinx.android.synthetic.main.item_clan_top_100.view.*

class ClanTop100Adapter(
    private val clans: List<ClanTop100>,
    private val serverCode: ServerCode,
    val context: Context
) :
    RecyclerView.Adapter<ClanTop100ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClanTop100ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_clan_top_100, parent, false)
        return ClanTop100ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return clans.size
    }

    override fun onBindViewHolder(holder: ClanTop100ViewHolder, position: Int) {
        val clan = clans[position]
        holder.let {
            it.bindView(clan, context)
        }
        holder.crdClanMemberTop100.setOnClickListener {
            val i = Intent(context.applicationContext, ClanStats::class.java)
            i.putExtra("clanName", clan.clan)
            i.putExtra("serverCode", serverCode.code)
            context.startActivity(i)
        }
    }

}


class ClanTop100ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameClan: TextView = itemView.lblNameClan
    private val rankClan: TextView = itemView.lblRankClan
    private val nameLeader: TextView = itemView.lblClanLeader
    private val clanCount: TextView = itemView.lblClanMemberCount
    private val clanPoints: TextView = itemView.lblClanPoints
    val crdClanMemberTop100: CardView = itemView.crdClanMemberTop100

    @SuppressLint("SetTextI18n")
    fun bindView(clan: ClanTop100, context: Context) {
        nameClan.text = clan.clan
        rankClan.text = "#${clan.rank}"
        nameLeader.text = "${context.getString(R.string.clan_leader)}: ${clan.clanLeader}"
        clanCount.text = "${context.getString(R.string.members)}: ${clan.members}"
        clanPoints.text =
            "${context.getString(R.string.clan_points)}: ${Formatter.thousandFormat(clan.points?.toInt())}"
    }
}























