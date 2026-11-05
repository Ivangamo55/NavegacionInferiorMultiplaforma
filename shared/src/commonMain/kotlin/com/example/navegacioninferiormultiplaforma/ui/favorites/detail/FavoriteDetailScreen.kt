package com.example.navegacioninferiormultiplaforma.ui.favorites.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavoriteDetailRoute(
    favoriteId: Int,
    onBack: () -> Unit
) {
    val viewModel: FavoriteDetailViewModel = viewModel {
        FavoriteDetailViewModel(favoriteId = favoriteId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FavoriteDetailScreen(
        uiState = uiState,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteDetailScreen(
    uiState: FavoriteDetailUiState,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Detalle") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        // Flecha dibujada con texto para evitar una dependencia
                        // adicional de material-icons-extended.
                        Text(text = "←")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "ID: ${uiState.id}")
            Text(text = uiState.title)
            Text(text = uiState.description)
        }
    }
}
