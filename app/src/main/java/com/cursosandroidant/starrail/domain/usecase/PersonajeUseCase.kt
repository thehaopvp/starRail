package com.cursosandroidant.starrail.domain.usecase

import com.cursosandroidant.starrail.data.model.Personaje
import com.cursosandroidant.starrail.domain.repositories.GetAllPersonajesImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllPersonajesRep

class GetAllPersonajesUseCase(private val getAllPersonajesRep: GetAllPersonajesRep) {

    suspend fun getAllPersonajes():List<Personaje>{
        return getAllPersonajesRep.getAllPersonajesImpl()
    }
}

class GetAllPersonajesImgUseCase(private val getAllPersonajesImgRep: GetAllPersonajesImgRep) {
  suspend fun getAllPersonajesImg(name:String):String?{
      return getAllPersonajesImgRep.getAllPersonajesImgImpl(name)
  }

}