package com.cursosandroidant.starrail.data.apiClient

import android.util.Log
import com.cursosandroidant.starrail.data.model.Personaje
import com.cursosandroidant.starrail.data.network.FirebaseDbInstance
import com.cursosandroidant.starrail.domain.repositories.GetAllPersonajesImgRep
import com.cursosandroidant.starrail.domain.repositories.GetAllPersonajesRep
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class GetAllPersonajesImpl: GetAllPersonajesRep {

    override suspend fun getAllPersonajesImpl():List<Personaje> {
        val firebaseFirestore = FirebaseDbInstance.getInstance()
        FirebaseDbInstance.initPlayIntegrity()
        FirebaseDbInstance.authenticateAnonymously()
        return withContext(Dispatchers.IO) {
            try {
                val documentSnapshot = firebaseFirestore.collection("personajes").get().await()

                documentSnapshot.toObjects(Personaje::class.java)
            } catch (e: FirebaseFirestoreException) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}

class GetAllPersonajesImgImpl: GetAllPersonajesImgRep {
    override suspend fun getAllPersonajesImgImpl(name: String): String? {
        try {

            val storageRef =
                FirebaseStorage.getInstance().getReference().child("characterImage/$name.jpeg")

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




//suspend fun getOnePersonajesFromFireStore(name: String): Personaje {
//    val firebaseFirestore = FirebaseDbInstance.getInstance()
//    var result = Personaje(
//        "", "", "", "", 0, "", "", "", "", "", listOf("1", "2", "3", "4", "5", "6")
//    )
//    return withContext(Dispatchers.IO) {
//        try {
//            val documentSnapshot =
//                firebaseFirestore.collection("personajes/$name").document(name).get().await()
//            result = documentSnapshot.toObject<Personaje>(Personaje::class.java)!!
//
//            return@withContext result
//        } catch (e: FirebaseFirestoreException) {
//            return@withContext result
//        }
//    }
//}