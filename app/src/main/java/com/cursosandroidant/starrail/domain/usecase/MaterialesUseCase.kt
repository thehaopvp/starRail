package com.cursosandroidant.starrail.domain.usecase

import com.cursosandroidant.starrail.data.model.Material
import com.cursosandroidant.starrail.data.model.Personaje
import com.cursosandroidant.starrail.domain.repositories.GetAllMaterialesImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllMaterialesRep
import com.cursosandroidant.starrail.domain.repositories.GetCharactersUsingMaterialRep
import com.cursosandroidant.starrail.domain.repositories.GetOneMaterialesRep

class GetAllMaterialesUseCase(private val getAllMaterialesRep: GetAllMaterialesRep) {

    suspend fun getAllMateriales():List<Material>{
        return getAllMaterialesRep.getAllMaterialesImp()
    }
}

class GetAllMaterialesImgUseCase(private val getAllMaterialesImgRep: GetAllMaterialesImgRep) {
    suspend fun getAllMaterialesImg(name:String):String?{
        return getAllMaterialesImgRep.getAllMaterialesImgImp(name)
    }

}

class GetCharactersUsingMaterialUseCase(private val getCharactersUsingMaterialRep: GetCharactersUsingMaterialRep) {
    suspend fun getAllMaterialesImg(name:String):List<Personaje>{
        return getCharactersUsingMaterialRep.getCharactersUsingMaterialRepImp(name)
    }

}

class GetOneMaterialesUseCase(private val GetOneMaterialesRep: GetOneMaterialesRep) {

    suspend fun getOneMateriales(name: String):Material{
        return GetOneMaterialesRep.getOneMaterialesImp(name)
    }
}