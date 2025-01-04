package com.cursosandroidant.starrail.data.model

import com.cursosandroidant.starrail.ui.components.ConArtefacto


data class Artefacto(
    val _2_piezas: String = "",
    val _4_piezas: String = "",
    val estrella: Int = 0,
    val imageUrl: String = "",
    override val nombre: String = "",
    val piezas: List<String>? = null
) : ConArtefacto
