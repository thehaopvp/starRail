package com.cursosandroidant.starrail.ui.presentation.artefactos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosandroidant.starrail.data.model.Artefacto
import com.cursosandroidant.starrail.domain.usecase.GetAllArtefactoImgUseCase
import com.cursosandroidant.starrail.domain.usecase.GetAllArtefactoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArtefactosViewModel(private val getAllArtefactoUseCase: GetAllArtefactoUseCase,private val getAllArtefactoImgUseCase: GetAllArtefactoImgUseCase) : ViewModel() {

    private val _artefactosList = MutableStateFlow<List<Artefacto>>(emptyList())
    val artefactosLista: StateFlow<List<Artefacto>> get() = _artefactosList

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        fetchAllArtefactos()
    }

    private fun fetchAllArtefactos() {
        viewModelScope.launch {
            // Verifica si _isLoading.value no es null y es false antes de continuar
            if (_isLoading.value == false) {
                _isLoading.value = true
                try {
                    val artefactos = getAllArtefactoUseCase.getAllArtefacto()
                    // Fetch images for each personaje
                    val artefactosWithImages = artefactos.map { artefacto ->
                        val imageUrl = getAllArtefactoImgUseCase.getAllArtefactoImg(artefacto.nombre)
                        // Si imageUrl es null, no afecta a la lista resultante
                        artefacto.copy(imageUrl = imageUrl ?: "") // Maneja el caso en que imageUrl sea null
                    }
                    _artefactosList.value = artefactosWithImages
                } catch (e: Exception) {
                    Log.e("FetchError", "Error fetching personajes", e)
                    _artefactosList.value = emptyList()
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }
}