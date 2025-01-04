package com.cursosandroidant.starrail.data.model


import com.cursosandroidant.starrail.ui.components.ConPersonaje

data class Personaje(
    override val nombre: String = "",
    override val via: String = "",
    val sexo: String = "",
    val mundo: String = "",
    val estrella: Int = 0,
    val faccion: String = "",
    val especie: String = "",
    val imageUrl: String? = null,
    override val elemento: String = "",
    val descripcion: String = "",
    val eidolon: List<String>? = null,
    val materiales: List<String>? = null
) : ConPersonaje