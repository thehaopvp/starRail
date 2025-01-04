package com.cursosandroidant.starrail.data.model

import com.cursosandroidant.starrail.ui.components.ConMaterial

data class Material(
    val curiosidad: String = "",
    val descripcion: String = "",
    val estrella: Int = 0,
    val imageUrl: String = "",
    override val nombre: String = "",
    val obtencion: List<String>? = null,
    val personajes: List<String>? = null
) : ConMaterial
