package com.nomad.wfstats.models.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nomad.wfstats.R
import com.nomad.wfstats.models.Warbox
import kotlinx.android.synthetic.main.item_warbox.view.*

class WarboxAdapter(
    private val warboxes: List<Warbox>,
    val context: Context
) : RecyclerView.Adapter<WarboxViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WarboxViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_warbox, parent, false)
        return WarboxViewHolder(view)
    }

    override fun getItemCount(): Int {
        return warboxes.size
    }

    override fun onBindViewHolder(holder: WarboxViewHolder, position: Int) {
        val warbox = warboxes[position]
        holder.let { it.bindView(warbox, context) }
    }
}


class WarboxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.lblWarboxName
    private val image: ImageView = itemView.imgWarbox

    fun bindView(warbox: Warbox, context: Context) {
        name.text = warbox.name
    }
}