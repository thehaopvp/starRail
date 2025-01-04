package com.cursosandroidant.starrail.domain.repositories

import com.cursosandroidant.starrail.data.model.Material
import com.cursosandroidant.starrail.data.model.Personaje


interface GetAllMaterialesRep {
    suspend fun getAllMaterialesImp():List<Material>
}

interface GetAllMaterialesImgRep {
    suspend fun getAllMaterialesImgImp(name:String):String?
}
interface GetCharactersUsingMaterialRep {
    suspend fun getCharactersUsingMaterialRepImp(name:String):List<Personaje>
}