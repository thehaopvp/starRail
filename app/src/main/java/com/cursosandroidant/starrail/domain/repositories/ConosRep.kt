package com.cursosandroidant.starrail.domain.repositories

import com.cursosandroidant.starrail.data.model.Cono

interface GetAllConosRep {
    suspend fun getAllConosImp():List<Cono>
}

interface GetAllConosImgRep {
    suspend fun getAllConosImgImp(name:String):String?
}