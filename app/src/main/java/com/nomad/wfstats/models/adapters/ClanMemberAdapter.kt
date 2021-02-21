package com.nomad.wfstats.models.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nomad.wfstats.R
import com.nomad.wfstats.models.ClanMember
import com.nomad.wfstats.models.util.Formatter
import com.nomad.wfstats.ui.PlayerStats
import kotlinx.android.synthetic.main.item_clan_member.view.*

class ClanMemberAdapter(
    private val members: List<ClanMember>,
    private val serverCode: ServerCode,
    val context: Context
) : RecyclerView.Adapter<ClanMemberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClanMemberViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_clan_member, parent, false)
        return ClanMemberViewHolder(view)
    }

    override fun getItemCount(): Int {
        return members.size
    }

    override fun onBindViewHolder(holder: ClanMemberViewHolder, position: Int) {
        val member = members[position]
        holder.let {
            it.bindView(member, context)
        }
        holder.crdClanMember.setOnClickListener {
            val i = Intent(context.applicationContext, PlayerStats::class.java)
            i.putExtra("playerName", member.nickname)
            i.putExtra("serverCode", serverCode.code)
            context.startActivity(i)
        }
    }
}

class ClanMemberViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    private val memberName: TextView = itemview.lblMemberName
    private val memberPoints: TextView = itemview.lblMemberClanPoints
    private val memberRole: ImageView = itemview.imgMemberRole
    val crdClanMember: CardView = itemview.crdClanMemberTop100

    @SuppressLint("SetTextI18n")
    fun bindView(member: ClanMember, context: Context) {
        memberName.text = member.nickname
        memberPoints.text =
            "${context.getString(R.string.clan_points)}: ${Formatter.thousandFormat(member.clan_points?.toInt())}"
        when (member.clan_role) {
            "MASTER" -> Glide.with(context)
                .load("https://i.imgur.com/MLPiD7a.png")
                .into(memberRole)
            "OFFICER" -> Glide.with(context)
                .load("https://i.imgur.com/VYxMTX9.png")
                .into(memberRole)
            "REGULAR" -> memberRole.visibility = View.INVISIBLE
        }


    }

}


















