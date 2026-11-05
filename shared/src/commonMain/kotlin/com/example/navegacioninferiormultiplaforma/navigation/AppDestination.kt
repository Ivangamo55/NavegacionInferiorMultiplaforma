package com.example.navegacioninferiormultiplaforma.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppDestination : NavKey {

    @Serializable
    data object Home : AppDestination

    @Serializable
    data object Favorites : AppDestination

    @Serializable
    data class FavoriteDetail(
        val favoriteId: Int
    ) : AppDestination

    @Serializable
    data object Profile : AppDestination
}
