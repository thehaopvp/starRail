package com.cursosandroidant.starrail.data.apiClient

import android.util.Log
import com.cursosandroidant.starrail.data.model.Material
import com.cursosandroidant.starrail.data.model.Personaje
import com.cursosandroidant.starrail.data.network.FirebaseDbInstance
import com.cursosandroidant.starrail.domain.repositories.GetAllMaterialesImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllMaterialesRep
import com.cursosandroidant.starrail.domain.repositories.GetAllPersonajesRep
import com.cursosandroidant.starrail.domain.repositories.GetCharactersUsingMaterialRep
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class GetCharactersUsingMaterialRepImp : GetCharactersUsingMaterialRep {
    override suspend fun getCharactersUsingMaterialRepImp(name: String): List<Personaje> {
        val firebaseFirestore = FirebaseDbInstance.getInstance()

        return withContext(Dispatchers.IO) {
            try {
                val documentSnapshot = firebaseFirestore.collection("personajes").get().await()
                  ///  .whereArrayContains("materiales", materialId)

                documentSnapshot.toObjects(Personaje::class.java)
            } catch (e: FirebaseFirestoreException) {
                e.printStackTrace()
                Log.e("Firestore", "Error al obtener personajes", e)
                emptyList()
            }
        }
    }

}

class GetAllMaterialesImp : GetAllMaterialesRep {
    override suspend fun getAllMaterialesImp(): List<Material> {
        val firebaseFirestore = FirebaseDbInstance.getInstance()
        return withContext(Dispatchers.IO) {
            try {
                val documentSnapshot = firebaseFirestore.collection("materiales").get().await()

                documentSnapshot.toObjects(Material::class.java)
            } catch (e: FirebaseFirestoreException) {
                e.printStackTrace()
                emptyList()
            }
        }
    }

}

class GetAllMaterialesImgImp : GetAllMaterialesImgRep {
    override suspend fun getAllMaterialesImgImp(name: String): String? {
        try {

            val storageRef =
                FirebaseStorage.getInstance().getReference().child("materialesImagen/$name.jpeg")

            return withContext(Dispatchers.IO) {
                try {
                    val url = storageRef.downloadUrl.await()
                    url.toString()
                } catch (e: Exception) {
                    Log.e("Error", e.toString())
                    null
                }
            }

        } catch (e: ArithmeticException) {
            Log.e("Error", "User UID is null")
            return null
        }
    }

}