package com.nomad.wfstats.models.util

import android.content.Context
import java.text.NumberFormat

class Formatter {
    companion object {
        fun thousandFormat(number: Int?): String? {
            return NumberFormat.getIntegerInstance().format(number)
        }

        fun getTimeFromSeconds(seconds: Int?): String? {
            if (seconds == null) return ""

            val h = ((seconds!! / 3600) / 10).toInt()
            val m = (seconds % 3600) / 60
            return "${h}h${m}m"
        }
    }
}