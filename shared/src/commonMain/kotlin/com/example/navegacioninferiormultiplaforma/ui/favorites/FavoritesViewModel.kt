package com.example.navegacioninferiormultiplaforma.ui.favorites

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow(FavoritesUiState())

    val uiState: StateFlow<FavoritesUiState> =
        _uiState.asStateFlow()
}