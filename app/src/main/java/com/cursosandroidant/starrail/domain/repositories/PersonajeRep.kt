package com.cursosandroidant.starrail.domain.repositories

import com.cursosandroidant.starrail.data.model.Personaje

interface GetAllPersonajesRep {

    suspend fun getAllPersonajesImpl() :List<Personaje>
}

interface GetAllPersonajesImgRep{

    suspend fun getAllPersonajesImgImpl(name:String) : String?
}