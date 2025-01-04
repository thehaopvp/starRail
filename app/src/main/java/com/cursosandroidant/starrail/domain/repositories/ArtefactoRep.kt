package com.cursosandroidant.starrail.domain.repositories

import com.cursosandroidant.starrail.data.model.Artefacto
import com.cursosandroidant.starrail.data.model.Cono



interface GetAllArtefactoRep {
    suspend fun getAllArtefactoImp():List<Artefacto>
}

interface GetAllArtefactoImgRep {
    suspend fun getAllArtefactoImgImp(name:String):String?
}