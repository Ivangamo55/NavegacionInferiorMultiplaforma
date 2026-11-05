package com.example.navegacioninferiormultiplaforma.ui.favorites.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoriteDetailViewModel(
    favoriteId: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(createUiState(favoriteId))

    val uiState: StateFlow<FavoriteDetailUiState> = _uiState.asStateFlow()

    private fun createUiState(favoriteId: Int): FavoriteDetailUiState {
        return when (favoriteId) {
            1 -> FavoriteDetailUiState(
                id = 1,
                title = "Jetpack Compose",
                description = "Framework declarativo de Android para construir interfaces de usuario."
            )

            2 -> FavoriteDetailUiState(
                id = 2,
                title = "Kotlin",
                description = "Lenguaje moderno, conciso y seguro utilizado oficialmente para Android."
            )

            3 -> FavoriteDetailUiState(
                id = 3,
                title = "Material Design",
                description = "Sistema de diseño que proporciona componentes, estilos y patrones de interfaz."
            )

            else -> FavoriteDetailUiState(
                id = favoriteId,
                title = "Favorito no encontrado",
                description = "No existe información para este elemento."
            )
        }
    }
}
