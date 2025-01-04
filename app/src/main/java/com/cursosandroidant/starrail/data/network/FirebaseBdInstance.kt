package com.cursosandroidant.starrail.data.network

import android.util.Log
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Singleton

@Singleton
object FirebaseDbInstance {
    private var firebaseDatabase: FirebaseFirestore? = null

    fun getInstance(): FirebaseFirestore {
        synchronized(this) {
            if (firebaseDatabase == null) {
                firebaseDatabase = FirebaseFirestore.getInstance()
            }
            return firebaseDatabase!!
        }
    }


    suspend fun authenticateAnonymously() {
        val mAuth = FirebaseAuth.getInstance()
        if (mAuth.currentUser == null) {
            try {
                mAuth.signInAnonymously().await()
                Log.d("Auth", "Signed in anonymously: ${mAuth.currentUser?.uid}")
            } catch (e: FirebaseAuthException) {
                Log.e("AuthError", "FirebaseAuthException during anonymous sign-in: ${e.message}")
            } catch (e: Exception) {
                Log.e("AuthError", "General error during sign-in: ${e.message}")
            }
        }
    }


    fun initPlayIntegrity() {
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )
    }
}