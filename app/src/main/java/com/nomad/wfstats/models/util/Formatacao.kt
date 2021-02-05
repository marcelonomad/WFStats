package com.nomad.wfstats.models.util

import java.text.NumberFormat

class Formatacao {
    companion object {
        fun formatarNumero(numero: Int?): String? {
            return NumberFormat.getIntegerInstance().format(numero)
        }
    }
}