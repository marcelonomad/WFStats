package com.nomad.wfstats.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.faskn.lib.PieChart
import com.faskn.lib.Slice
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.nomad.wfstats.R
import com.nomad.wfstats.models.Headshots
import com.nomad.wfstats.models.PlayerDetails
import com.nomad.wfstats.models.adapters.CustomLegendAdapter
import com.nomad.wfstats.models.util.Formatter
import kotlinx.android.synthetic.main.activity_player_details_stats.*


class PlayerDetailsStats : AppCompatActivity() {

    lateinit var adView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_details_stats)

        val playerDetails = intent.getSerializableExtra("playerDetails") as? PlayerDetails

        adView = findViewById<View>(R.id.adPlayerDetailsStats) as AdView
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        val pieChart = PieChart(
            slices = provideSlices(getHeadshots(playerDetails!!)),
            clickListener = null,
            sliceStartPoint = 0f,
            sliceWidth = 80f
        ).build()
        chart.setPieChart(pieChart)
        chart.showLegend(legendLayout, CustomLegendAdapter())

        setupGeneralInfo(playerDetails)
        setupRifleman(playerDetails)
        setupMedic(playerDetails)
        setupEngineer(playerDetails)
        setupSniper(playerDetails)
        setupSED(playerDetails)

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

    private fun getHeadshots(playerDetails: PlayerDetails): List<Headshots> {
        val headshots = mutableListOf<Headshots>()

        headshots.add(Headshots(getString(R.string.recon), playerDetails.pvpReconHeadshots))
        headshots.add(Headshots(getString(R.string.medic), playerDetails.pvpMedicHeadshots))
        headshots.add(Headshots(getString(R.string.rifleman), playerDetails.pvpRiflemanHeadshots))
        headshots.add(Headshots(getString(R.string.engineer), playerDetails.pvpEngineerHeadshots))
        return headshots
    }

    @SuppressLint("SetTextI18n")
    private fun setupGeneralInfo(details: PlayerDetails) {
        lblClimbAssists.text =
            "${getString(R.string.climb_assists)}: ${Formatter.thousandFormat(details.climbAssists)}"
        lblCoopClimbs.text =
            "${getString(R.string.coop_climbs)}: ${Formatter.thousandFormat(details.climbCoop)}"
        lblPVPMelee.text =
            "${getString(R.string.pvp_melee_kills)}: ${Formatter.thousandFormat(details.pvpMelee)}"
        lblPvPKicked.text =
            "${getString(R.string.pvp_kicked)}: ${Formatter.thousandFormat(details.pvpKicked)}"
        lblPvPLeft.text =
            "${getString(R.string.pvp_left)}: ${Formatter.thousandFormat(details.pvpLeft)}"
    }

    @SuppressLint("SetTextI18n")
    private fun setupRifleman(details: PlayerDetails) {
        lblRiflemanPlaytime.text =
            "${getString(R.string.rifleman_playtime)}: ${Formatter.getTimeFromSeconds(details.pvpRiflemanPlaytime)}"
        lblRiflemanHeadshots.text =
            "${getString(R.string.headshots)}: ${Formatter.thousandFormat(details.pvpRiflemanHeadshots)}"
        lblAmmoRestored.text =
            "${getString(R.string.ammo_restored)}: ${Formatter.thousandFormat(details.ammoRestored)}"
    }

    @SuppressLint("SetTextI18n")
    private fun setupMedic(details: PlayerDetails) {
        lblMedicPlaytime.text =
            "${getString(R.string.medic_playtime)}: ${Formatter.getTimeFromSeconds(details.pvpMedicPlaytime)}"
        lblMedicHeadshots.text =
            "${getString(R.string.headshots)}: ${Formatter.thousandFormat(details.pvpMedicHeadshots)}"
        lblResurrectMade.text =
            "${getString(R.string.revive_made)}: ${Formatter.thousandFormat(details.resurrectMade)}"
        lblHPHealed.text =
            "${getString(R.string.hp_healed)}: ${Formatter.thousandFormat(details.heal)}"
    }

    @SuppressLint("SetTextI18n")
    private fun setupEngineer(details: PlayerDetails) {
        lblEngineerPlaytime.text =
            "${getString(R.string.engineer_playtime)}: ${Formatter.getTimeFromSeconds(details.pvpEngineerPlaytime)}"
        lblEngineerHeadshots.text =
            "${getString(R.string.headshots)}: ${Formatter.thousandFormat(details.pvpEngineerHeadshots)}"
        lblRepairMade.text =
            "${getString(R.string.repair_made)}: ${Formatter.thousandFormat(details.repair)}"
        lblClaymoreKills.text =
            "${getString(R.string.claymore_kills)}: ${Formatter.thousandFormat(details.engineerClaymore)}"
    }

    @SuppressLint("SetTextI18n")
    private fun setupSniper(details: PlayerDetails) {
        lblReconPlaytime.text =
            "${getString(R.string.recon_playtime)}: ${Formatter.getTimeFromSeconds(details.pvpReconPlaytime)}"
        lblSniperHeadshots.text =
            "${getString(R.string.headshots)}: ${Formatter.thousandFormat(details.pvpReconHeadshots)}"
    }

    private fun setupSED(details: PlayerDetails) {
        crdHeavy.visibility = View.GONE
        Log.d("SED", Formatter.getTimeFromSeconds(details.pvpHeavyPlaytime))
    }
}











