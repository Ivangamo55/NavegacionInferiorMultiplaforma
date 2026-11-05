package com.example.navegacioninferiormultiplaforma.ui.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavoritesRoute(
    onFavoriteClick: (Int) -> Unit,
    viewModel: FavoritesViewModel = viewModel { FavoritesViewModel() }
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FavoritesScreen(
        uiState = uiState,
        onFavoriteClick = onFavoriteClick
    )
}

@Composable
fun FavoritesScreen(
    uiState: FavoritesUiState,
    onFavoriteClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = uiState.title)

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                items = uiState.favorites,
                key = { favorite -> favorite.id }
            ) { favorite ->
                ListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onFavoriteClick(favorite.id) },
                    headlineContent = {
                        Text(text = favorite.name)
                    },
                    supportingContent = {
                        Text(text = favorite.description)
                    }
                )
                HorizontalDivider()
            }
        }
    }
}
