package com.nomad.wfstats.models

data class Warbox(
    var name: String,
    var type: WarboxType,
    var golder: Boolean,
    var quantityOpened: Int,
    var urlImage: String
)

enum class WarboxType {
    dolar, kredit, crown
}