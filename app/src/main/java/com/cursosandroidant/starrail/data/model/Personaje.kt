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
    val ascension: List<AscensionLevel>? = null,
    val habilidades: Habilidades? = null// Cambiado de materiales a ascension
) : ConPersonaje

data class AscensionLevel(
    val level: String = "", // Nivel de ascensión
    val materials: List<MaterialRequirement>? = null
// Lista de materiales necesarios
)

data class MaterialRequirement(
    val id: String = "",   // ID del material
    val material_necesario: String = "",  // Cantidad necesaria
    val material_img: String = ""
)

// Estructura de habilidades
data class Habilidades(
    val atq_basico: Habilidad? = Habilidad(),
    val habilidad_basica: Habilidad = Habilidad(),
    val habilidad_definitiva: Habilidad = Habilidad(),
    val tecnica: Habilidad = Habilidad(),
    val talento: Habilidad = Habilidad(),
    val mejoras: List<Mejora> = emptyList()
)

data class Habilidad(
    open val atq_aoe: String = "",
    open val atq_individual: String = "",
    open val breakValue: Int = 0,
    open val coste: Int = 0,
    val descripcion: String = "",
    val gen_energia: Int = 0,
    val img: String = "",
    val nombre: String = ""
)


data class Mejora(
    val descripcion: String = "",
    val nombre: String = ""
)