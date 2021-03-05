package com.nomad.wfstats.ui.warbox

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.nomad.wfstats.R
import kotlinx.android.synthetic.main.activity_open_warbox.*
import kotlin.random.Random

class OpenWarbox : AppCompatActivity() {
    private var warboxPurchased: Int = 0
    private lateinit var warbox1: PrizeProbability
    private lateinit var warbox2: PrizeProbability
    private lateinit var warbox3: PrizeProbability
    private lateinit var warbox4: PrizeProbability
    private lateinit var warbox5: PrizeProbability


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_warbox)

        imgWB1.setBackgroundResource(R.drawable.warbox_anim)
        imgWB2.setBackgroundResource(R.drawable.warbox_anim)
        imgWB3.setBackgroundResource(R.drawable.warbox_anim)
        imgWB4.setBackgroundResource(R.drawable.warbox_anim)
        imgWB5.setBackgroundResource(R.drawable.warbox_anim)
        imgWB1.visibility = View.INVISIBLE
        imgWB2.visibility = View.INVISIBLE
        imgWB3.visibility = View.INVISIBLE
        imgWB4.visibility = View.INVISIBLE
        imgWB5.visibility = View.INVISIBLE

        btnBuyBox.setOnClickListener {
            toggleBuy()
            warboxPurchased++
            boxPurchased(warboxPurchased)
            toggleBuy()
        }
        btnOpenBox.setOnClickListener {
            openAll()
        }
        btnGetPrizes.setOnClickListener {
            for (i in 1..warboxPurchased) {
                when (i) {
                    1 -> getPrizes(imgWB1)
                    2 -> getPrizes(imgWB2)
                    3 -> getPrizes(imgWB3)
                    4 -> getPrizes(imgWB4)
                    5 -> getPrizes(imgWB5)
                }
            }
            warboxPurchased = 0
        }
    }

    private fun boxPurchased(position: Int) {
        when (position) {
            1 -> {
                imgWB1.visibility = View.VISIBLE
            }
            2 -> {
                imgWB2.visibility = View.VISIBLE
            }
            3 -> {
                imgWB3.visibility = View.VISIBLE
            }
            4 -> {
                imgWB4.visibility = View.VISIBLE
            }
            5 -> {
                imgWB5.visibility = View.VISIBLE
            }

        }
    }

    private fun getPrizes(view: View) {
        stopAnimation(view)
    }

    private fun toggleBuy() {
        btnBuyBox.isEnabled = warboxPurchased <= 5
    }

    private fun openAll() {

        warbox1 = PrizeProbability()
        warbox2 = PrizeProbability()
        warbox3 = PrizeProbability()
        warbox4 = PrizeProbability()
        warbox5 = PrizeProbability()

        for (i in 1..warboxPurchased) {
            when (i) {
                1 -> {
                    openSingle(warbox1)
                    logPrize(warbox1, 1, lblWb1)
                    animateOpening(imgWB1)

                }
                2 -> {
                    openSingle(warbox2)
                    logPrize(warbox2, 2, lblWb2)
                    animateOpening(imgWB2)
                }
                3 -> {
                    openSingle(warbox3)
                    logPrize(warbox3, 3, lblWb3)
                    animateOpening(imgWB3)
                }
                4 -> {
                    openSingle(warbox4)
                    logPrize(warbox4, 4, lblWb4)
                    animateOpening(imgWB4)
                }
                5 -> {
                    openSingle(warbox5)
                    logPrize(warbox5, 5, lblWb5)
                    animateOpening(imgWB5)
                }
            }
        }
        //TODO("animations")
        // warboxPurchased = 0
        toggleBuy()
        Log.d("Prize ", "******************")

    }

    private fun openSingle(warbox: PrizeProbability) {
        var random: Int = Random.nextInt(0, 1000)
        when (random) {
            11 -> warbox.gold = true //1 number in 1000 = 0.1%
            in 0..10 -> warbox.permanent = true //10 numbers in 1000 = 1%
            in 12..250 -> warbox.oneDay = true
            in 251..500 -> warbox.threeHours = true
            else -> warbox.oneHour = true
        }
    }

    private fun animateOpening(view: View) {
        //view.setBackgroundResource(R.drawable.warbox_anim)
        val anim: AnimationDrawable = view.background as AnimationDrawable
        anim.start()
    }

    private fun stopAnimation(view: View) {
        val anim: AnimationDrawable = view.background as AnimationDrawable
        anim.stop()
        view.visibility = View.INVISIBLE
    }

    private fun logPrize(warbox: PrizeProbability, position: Int, label: TextView) {
        if (warbox.gold) {
            Log.d("Prize - ${position}: ", "GOLD")
            label.text = "Gold"
        }
        if (warbox.permanent) {
            Log.d("Prize - ${position}: ", "PERMANENT")
            label.text = "Permanent"
        }
        if (warbox.oneDay) {
            Log.d("Prize - ${position}: ", "ONE DAY")
            label.text = "One Day"
        }
        if (warbox.threeHours) {
            Log.d("Prize - ${position}: ", "THREE HOURS")
            label.text = "Three Hours"
        }
        if (warbox.oneHour) {
            Log.d("Prize - ${position}: ", "ONE HOUR")
            label.text = "One Hour"
        }
    }
}

class PrizeProbability(
    var oneHour: Boolean = false,
    var threeHours: Boolean = false,
    var oneDay: Boolean = false,
    var permanent: Boolean = false,
    var gold: Boolean = false
)
























