package com.cursosandroidant.starrail.ui.presentation.conos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursosandroidant.starrail.data.model.Cono
import com.cursosandroidant.starrail.domain.usecase.GetAllConosImgUseCase
import com.cursosandroidant.starrail.domain.usecase.GetAllConosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ConosViewModel(private val getAllConosUseCase: GetAllConosUseCase,private val getAllConosImgUseCase: GetAllConosImgUseCase):ViewModel() {
    private val _conosList = MutableStateFlow<List<Cono>>(emptyList())
    val conosLista: StateFlow<List<Cono>> get() = _conosList

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> get() = _isLoading


    init {
        fetchAllConos()
    }

    private fun fetchAllConos() {
        viewModelScope.launch {
            // Verifica si _isLoading.value no es null y es false antes de continuar
            if (_isLoading.value == false) {
                _isLoading.value = true
                try {
                    val artefactos = getAllConosUseCase.getAllConos()
                    // Fetch images for each personaje
                    val ConosWithImages = artefactos.map { cono ->
                        val imageUrl = getAllConosImgUseCase.getAllConosImg(cono.nombre)
                        // Si imageUrl es null, no afecta a la lista resultante
                        cono.copy(imageUrl = imageUrl ?: "") // Maneja el caso en que imageUrl sea null
                    }
                    _conosList.value = ConosWithImages
                } catch (e: Exception) {
                    Log.e("FetchError", "Error fetching personajes", e)
                    _conosList.value = emptyList()
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }
}