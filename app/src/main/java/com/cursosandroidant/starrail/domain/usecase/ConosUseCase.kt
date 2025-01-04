package com.cursosandroidant.starrail.domain.usecase

import com.cursosandroidant.starrail.data.model.Cono
import com.cursosandroidant.starrail.domain.repositories.GetAllConosImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllConosRep

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