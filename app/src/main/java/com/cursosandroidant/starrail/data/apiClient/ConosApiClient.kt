package com.cursosandroidant.starrail.data.apiClient

import android.util.Log
import com.cursosandroidant.starrail.data.model.Cono
import com.cursosandroidant.starrail.data.network.FirebaseDbInstance
import com.cursosandroidant.starrail.domain.repositories.GetAllConosImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllConosRep
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext



class GetAllConosImp: GetAllConosRep {
    override suspend fun getAllConosImp(): List<Cono> {
        val firebaseFirestore = FirebaseDbInstance.getInstance()
        return withContext(Dispatchers.IO) {
            try {
                val documentSnapshot = firebaseFirestore.collection("conos").get().await()

                documentSnapshot.toObjects(Cono::class.java)
            } catch (e: FirebaseFirestoreException) {
                e.printStackTrace()
                emptyList()
            }
        }
    }

}

class GetAllConosImgImp: GetAllConosImgRep {


    override suspend fun getAllConosImgImp(name: String): String? {
        try {

            val storageRef =
                FirebaseStorage.getInstance().getReference().child("conosImagen/$name.jpeg")

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