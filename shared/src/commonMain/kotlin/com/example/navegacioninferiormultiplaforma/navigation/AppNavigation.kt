package com.example.navegacioninferiormultiplaforma.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.example.navegacioninferiormultiplaforma.ui.favorites.FavoritesRoute
import com.example.navegacioninferiormultiplaforma.ui.favorites.detail.FavoriteDetailRoute
import com.example.navegacioninferiormultiplaforma.ui.home.HomeRoute
import com.example.navegacioninferiormultiplaforma.ui.profile.ProfileRoute
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

private val navigationConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(AppDestination.Home.serializer())
            subclass(AppDestination.Favorites.serializer())
            subclass(AppDestination.FavoriteDetail.serializer())
            subclass(AppDestination.Profile.serializer())
        }
    }
}

@Composable
fun AppNavigation() {
    // Los tres destinos de la barra inferior. FavoriteDetail NO es una pestaña.
    val destinations = listOf(
        AppDestination.Home,
        AppDestination.Favorites,
        AppDestination.Profile
    )

    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    // Una pila independiente para cada pestaña principal.
    val homeBackStack = rememberNavBackStack(
        navigationConfig,
        AppDestination.Home
    )
    val favoritesBackStack = rememberNavBackStack(
        navigationConfig,
        AppDestination.Favorites
    )
    val profileBackStack = rememberNavBackStack(
        navigationConfig,
        AppDestination.Profile
    )

    // Define qué pantalla corresponde a cada destino, incluido el detalle.
    val appEntryProvider = entryProvider<NavKey> {
        entry<AppDestination.Home> {
            HomeRoute()
        }

        entry<AppDestination.Favorites> {
            FavoritesRoute(
                onFavoriteClick = { favoriteId ->
                    favoritesBackStack.add(
                        AppDestination.FavoriteDetail(
                            favoriteId = favoriteId
                        )
                    )
                }
            )
        }

        entry<AppDestination.FavoriteDetail> { destination ->
            FavoriteDetailRoute(
                favoriteId = destination.favoriteId,
                onBack = {
                    if (favoritesBackStack.size > 1) {
                        favoritesBackStack.removeLastOrNull()
                    }
                }
            )
        }

        entry<AppDestination.Profile> {
            ProfileRoute()
        }
    }

    // Decorar cada pila por separado conserva el estado y los ViewModels.
    val homeEntries = rememberDecoratedNavEntries(
        backStack = homeBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = appEntryProvider
    )

    val favoritesEntries = rememberDecoratedNavEntries(
        backStack = favoritesBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = appEntryProvider
    )

    val profileEntries = rememberDecoratedNavEntries(
        backStack = profileBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = appEntryProvider
    )

    val currentBackStack = when (selectedIndex) {
        0 -> homeBackStack
        1 -> favoritesBackStack
        else -> profileBackStack
    }

    val currentEntries = when (selectedIndex) {
        0 -> homeEntries
        1 -> favoritesEntries
        else -> profileEntries
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                destinations.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            when (destination) {
                                AppDestination.Home -> Text("⌂")
                                AppDestination.Favorites -> Text("♥")
                                AppDestination.Profile -> Text("●")
                                // No se muestra en la barra, pero el when debe
                                // contemplar todos los tipos de AppDestination.
                                is AppDestination.FavoriteDetail -> Text("•")
                            }
                        },
                        label = {
                            Text(
                                text = when (destination) {
                                    AppDestination.Home -> "Inicio"
                                    AppDestination.Favorites -> "Favoritos"
                                    AppDestination.Profile -> "Perfil"
                                    is AppDestination.FavoriteDetail -> "Detalle"
                                }
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavDisplay(
            entries = currentEntries,
            onBack = {
                if (currentBackStack.size > 1) {
                    currentBackStack.removeLastOrNull()
                } else if (selectedIndex != 0) {
                    selectedIndex = 0
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
