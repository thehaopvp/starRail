package com.cursosandroidant.starrail.ui.presentation.personaje

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosandroidant.starrail.data.model.Personaje
import com.cursosandroidant.starrail.data.model.Post
import com.cursosandroidant.starrail.domain.repositories.ApiService
import com.cursosandroidant.starrail.domain.usecase.GetAllPersonajesImgUseCase
import com.cursosandroidant.starrail.domain.usecase.GetAllPersonajesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class PersonajeViewModel(
    private val personajesUseCase: GetAllPersonajesUseCase,
    private val getAllPersonajesImgUseCase: GetAllPersonajesImgUseCase,
    private val retrofit: Retrofit
) : ViewModel() {


    private val _personajeList = MutableStateFlow<List<Personaje>>(emptyList())
    val personajeLista: StateFlow<List<Personaje>> = _personajeList

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading


    private val retrofitList = MutableStateFlow<List<Post>>(emptyList())
    val retrofitLista: StateFlow<List<Post>> get() = retrofitList


    init {
        fetchAllPersonajes()
        fetchRetrofit()
    }

    private fun fetchAllPersonajes() {
        viewModelScope.launch {
            // Verifica si _isLoading.value no es null y es false antes de continuar
            if (_isLoading.value == false) {
                _isLoading.value = true
                try {
                    val personajes = personajesUseCase.getAllPersonajes()
                    // Fetch images for each personaje
                    val personajesWithImages = personajes.map { personaje ->
                        val imageUrl = getAllPersonajesImgUseCase.getAllPersonajesImg(personaje.nombre)
                        // Si imageUrl es null, no afecta a la lista resultante
                        personaje.copy(
                            imageUrl = imageUrl ?: ""
                        ) // Maneja el caso en que imageUrl sea null
                    }
                    _personajeList.value = personajesWithImages
                } catch (e: Exception) {
                    Log.e("FetchError", "Error fetching personajes", e)
                    _personajeList.value = emptyList()
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }


    private fun fetchRetrofit()
    {
         val apiService = retrofit.create(ApiService::class.java)
        viewModelScope.launch {
            try {
                val posts = apiService.getPosts() // Esto ya es asíncrono gracias a `suspend`
                retrofitList.value = posts
            } catch (e: Exception) {
                Log.d("Error retrofit","error retrofit")
            }
        }

    }

//    private fun fetchOnePersonajes(name:String) {
//        viewModelScope.launch {
//            // Verifica si _isLoading.value no es null y es false antes de continuar
//            if (_isLoading.value == false) {
//                _isLoading.value = true
//                try {
//                    val personajes = getOnePersonajesFromFireStore(name)
//                    // Fetch images for each personaje
//                    val personajesWithImages = personajes.let { personaje ->
//                        val imageUrl = getCharacterImage(personaje.nombre)
//                        // Si imageUrl es null, no afecta a la lista resultante
//                        personaje.copy(imageUrl = imageUrl ?: "") // Maneja el caso en que imageUrl sea null
//                    }
//                    _personajeList.value = personajesWithImages
//                } catch (e: Exception) {
//                    Log.e("FetchError", "Error fetching personajes", e)
//                    _personajeList.value = emptyList()
//                } finally {
//                    _isLoading.value = false
//                }
//            }
//        }
//    }

}