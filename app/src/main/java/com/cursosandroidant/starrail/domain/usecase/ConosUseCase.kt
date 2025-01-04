package com.cursosandroidant.starrail.domain.usecase

import com.cursosandroidant.starrail.data.model.Cono
import com.cursosandroidant.starrail.data.model.Personaje
import com.cursosandroidant.starrail.domain.repositories.GetAllConosImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllConosRep
import com.cursosandroidant.starrail.domain.repositories.GetAllPersonajesImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllPersonajesRep

class GetAllConosUseCase(private val getAllConosRep: GetAllConosRep) {

    suspend fun getAllConos():List<Cono>{
        return getAllConosRep.getAllConosImp()
    }
}

class GetAllConosImgUseCase(private val getAllConosImgRep: GetAllConosImgRep) {
    suspend fun getAllConosImg(name:String):String?{
        return getAllConosImgRep.getAllConosImgImp(name)
    }

}