package com.example.navegacioninferiormultiplaforma.ui.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun FavoritesRoute(
    //viewModel: FavoritesViewModel = viewModel()
    viewModel: FavoritesViewModel = viewModel { FavoritesViewModel() }
) {

    val uiState by
    viewModel.uiState.collectAsStateWithLifecycle()

    FavoritesScreen(
        uiState = uiState
    )
}


@Composable
fun FavoritesScreen(
    uiState: FavoritesUiState
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = uiState.title
        )

        Text(
            text = "Elementos favoritos: ${uiState.favoritesCount}"
        )
    }
}