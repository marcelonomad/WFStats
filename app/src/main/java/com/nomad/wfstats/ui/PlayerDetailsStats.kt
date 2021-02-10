package com.nomad.wfstats.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.faskn.lib.PieChart
import com.faskn.lib.Slice
import com.faskn.lib.buildChart
import com.nomad.wfstats.R
import com.nomad.wfstats.models.Headshots
import com.nomad.wfstats.models.adapters.CustomLegendAdapter
import kotlinx.android.synthetic.main.activity_player_details_stats.*
import kotlin.random.Random


class PlayerDetailsStats : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details_stats)

        val headshots = intent.getSerializableExtra("headshotspvp") as? List<Headshots>


        val pieChart = PieChart(
            slices = provideSlices(headshots!!.toList()),
            clickListener = null,
            sliceStartPoint = 0f,
            sliceWidth = 80f
        ).build()

        chart.setPieChart(pieChart)
        chart.showLegend(legendLayout, CustomLegendAdapter())

    }

    private fun provideSlices(headshots: List<Headshots>): ArrayList<Slice> {

        val array = ArrayList<Slice>()
        val colors =
            arrayOf(R.color.sniper, R.color.medic, R.color.rifleman, R.color.engineer)

        for (i: Int in 0..3) {
            array.add(Slice(headshots[i].count.toFloat(), colors[i], headshots[i].className))
        }
        return array
    }
}











