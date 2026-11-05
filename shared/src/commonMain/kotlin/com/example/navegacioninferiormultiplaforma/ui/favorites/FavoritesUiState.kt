package com.example.navegacioninferiormultiplaforma.ui.favorites

data class FavoriteItemUiModel(
    val id: Int,
    val name: String,
    val description: String
)

data class FavoritesUiState(
    val title: String = "Favoritos",
    val favorites: List<FavoriteItemUiModel> = emptyList()
)
