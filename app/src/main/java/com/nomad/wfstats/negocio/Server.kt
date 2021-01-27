package com.nomad.wfstats.negocio

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.nomad.wfstats.R

class Server(val context: Context, var servers: Array<ServerCode>) : BaseAdapter() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.itemserver, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.nameServer.text = servers[position].name
        var id: Int
        if (servers[position] == ServerCode.na)
            id = R.drawable.us
        else id = R.drawable.eubr
        vh.imageServer.setBackgroundResource(id)
        return view
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return servers.size
    }

    private class ItemHolder(row: View?) {
        val nameServer: TextView = row?.findViewById(R.id.lblServerName) as TextView
        val imageServer: ImageView = row?.findViewById(R.id.imgServer) as ImageView
    }
}

enum class ServerCode() {
    na, eu
}