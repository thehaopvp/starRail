package com.cursosandroidant.starrail.data.model

import com.cursosandroidant.starrail.ui.components.ConCono

data class Cono(
    override val via: String = "",
    override val nombre: String = "",
    val imageUrl: String = "",
    val habilidad: String = "",
    val estrella: Int = 0,

    ) : ConCono
