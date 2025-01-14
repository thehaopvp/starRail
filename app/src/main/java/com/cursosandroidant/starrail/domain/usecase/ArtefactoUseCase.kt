package com.cursosandroidant.starrail.domain.usecase

import com.cursosandroidant.starrail.data.model.Artefacto
import com.cursosandroidant.starrail.domain.repositories.GetAllArtefactoImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllArtefactoRep

class GetAllArtefactoUseCase(private val getAllArtefactoRep: GetAllArtefactoRep) {

    suspend fun getAllArtefacto():List<Artefacto>{
        return getAllArtefactoRep.getAllArtefactoImp()
    }
}

class GetAllArtefactoImgUseCase(private val getAllArtefactoImgRep: GetAllArtefactoImgRep) {
    suspend fun getAllArtefactoImg(name:String):String?{
        return getAllArtefactoImgRep.getAllArtefactoImgImp(name)
    }

}