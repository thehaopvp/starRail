package com.cursosandroidant.starrail.data.apiClient

import android.util.Log
import com.cursosandroidant.starrail.data.model.Artefacto
import com.cursosandroidant.starrail.data.network.FirebaseDbInstance
import com.cursosandroidant.starrail.domain.repositories.GetAllArtefactoImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllArtefactoRep
import com.cursosandroidant.starrail.domain.repositories.GetAllMaterialesRep
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class getAllArtefactoImp : GetAllArtefactoRep {
    override suspend fun getAllArtefactoImp(): List<Artefacto> {
        val firebaseFirestore = FirebaseDbInstance.getInstance()
        return withContext(Dispatchers.IO) {
            try {
                val documentSnapshot = firebaseFirestore.collection("artefactos").get().await()

                documentSnapshot.toObjects(Artefacto::class.java)
            } catch (e: FirebaseFirestoreException) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}

class getAllArtefactoImgImp : GetAllArtefactoImgRep
{
    override suspend fun getAllArtefactoImgImp(name: String): String? {
        try {

            val storageRef =
                FirebaseStorage.getInstance().getReference().child("artefactosImagen/$name.jpeg")

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