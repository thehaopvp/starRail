package com.cursosandroidant.starrail.ui.presentation.materiales

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosandroidant.starrail.data.model.Material
import com.cursosandroidant.starrail.data.model.Personaje
import com.cursosandroidant.starrail.domain.usecase.GetAllMaterialesImgUseCase
import com.cursosandroidant.starrail.domain.usecase.GetAllMaterialesUseCase
import com.cursosandroidant.starrail.domain.usecase.GetAllPersonajesImgUseCase
import com.cursosandroidant.starrail.domain.usecase.GetCharactersUsingMaterialUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

class MaterialesViewModel(private val getAllMaterialesUseCase: GetAllMaterialesUseCase
,private val getAllMaterialesImgUseCase: GetAllMaterialesImgUseCase,
    private val getCharactersUsingMaterialUseCase: GetCharactersUsingMaterialUseCase,
    private val getAllPersonajesImgUseCase: GetAllPersonajesImgUseCase

): ViewModel() {
    private val _materialesList = MutableStateFlow<List<Material>>(emptyList())
    val materialesLista: StateFlow<List<Material>> get() = _materialesList

    private val _personajeList = MutableStateFlow<List<Personaje>>(emptyList())
    val personajeLista: StateFlow<List<Personaje>> get() = _personajeList

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        fetchAllMateriales()
    }

    private fun fetchAllMateriales() {
        viewModelScope.launch {
            // Verifica si _isLoading.value no es null y es false antes de continuar
            if (_isLoading.value == false) {
                _isLoading.value = true
                try {
                    val materiales = getAllMaterialesUseCase.getAllMateriales()

                    val materialesWithImages = materiales.map { material ->
                        val imageUrl = getAllMaterialesImgUseCase.getAllMaterialesImg(material.nombre)
                        // Si imageUrl es null, no afecta a la lista resultante
                        material.copy(imageUrl = imageUrl ?: "") // Maneja el caso en que imageUrl sea null
                    }
                    _materialesList.value = materialesWithImages
                } catch (e: Exception) {
                    Log.e("FetchError", "Error fetching personajes", e)
                    _materialesList.value = emptyList()
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }

    fun fetchPersonajesConMaterial(materialId: String) {
        viewModelScope.launch {
            // Verifica si _isLoading.value no es null y es false antes de continuar
            if (_isLoading.value == false) {
                _isLoading.value = true
                try {

                    val personajes = getCharactersUsingMaterialUseCase.getAllMaterialesImg(materialId)
                    // Fetch images for each personaje
                    val personajesWithImages = personajes.map { personaje ->
                        val imageUrl = getAllPersonajesImgUseCase.getAllPersonajesImg(personaje.nombre)
                        // Si imageUrl es null, no afecta a la lista resultante
                        personaje.copy(imageUrl = imageUrl ?: "") // Maneja el caso en que imageUrl sea null
                    }
                    _personajeList.value = personajes
                } catch (e: Exception) {
                    Log.e("FetchError", "Error fetching personajes", e)
                    _personajeList.value = emptyList()
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }




//    private suspend fun getPersonajesUsandoMaterial(materialId: String): List<Personaje>  {
//        return   getCharactersUsingMaterial(materialId)
//    }
}