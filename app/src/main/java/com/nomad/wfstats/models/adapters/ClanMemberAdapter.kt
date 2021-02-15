package com.nomad.wfstats.models.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nomad.wfstats.R
import com.nomad.wfstats.models.ClanMember
import com.nomad.wfstats.models.util.Formatter
import kotlinx.android.synthetic.main.item_clan_member.view.*

class ClanMemberAdapter(
    private val members: List<ClanMember>,
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
    }
}

class ClanMemberViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    private val memberName: TextView = itemview.lblMemberName
    private val memberPoints: TextView = itemview.lblMemberClanPoints
    private val memberRole: ImageView = itemview.imgMemberRole

    @SuppressLint("SetTextI18n")
    fun bindView(member: ClanMember, context: Context) {
        memberName.text = member.nickname
        memberPoints.text = "Points: ${Formatter.thousandFormat(member.clan_points?.toInt())}"
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


















