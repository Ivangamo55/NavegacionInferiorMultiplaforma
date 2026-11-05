package com.example.navegacioninferiormultiplaforma.ui.favorites

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        FavoritesUiState(
            favorites = listOf(
                FavoriteItemUiModel(
                    id = 1,
                    name = "Jetpack Compose",
                    description = "Desarrollo declarativo de interfaces"
                ),
                FavoriteItemUiModel(
                    id = 2,
                    name = "Kotlin",
                    description = "Lenguaje moderno para Android"
                ),
                FavoriteItemUiModel(
                    id = 3,
                    name = "Material Design",
                    description = "Sistema de diseño para interfaces"
                )
            )
        )
    )

    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()
}
