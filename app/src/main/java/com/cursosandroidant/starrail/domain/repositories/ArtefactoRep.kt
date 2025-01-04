package com.cursosandroidant.starrail.domain.repositories

import com.cursosandroidant.starrail.data.model.Artefacto



interface GetAllArtefactoRep {
    suspend fun getAllArtefactoImp():List<Artefacto>
}

interface GetAllArtefactoImgRep {
    suspend fun getAllArtefactoImgImp(name:String):String?
}