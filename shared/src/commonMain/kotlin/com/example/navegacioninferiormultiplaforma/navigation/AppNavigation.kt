package com.example.navegacioninferiormultiplaforma.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator

import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay

import androidx.savedstate.serialization.SavedStateConfiguration

import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

import com.example.navegacioninferiormultiplaforma.ui.home.HomeRoute
import com.example.navegacioninferiormultiplaforma.ui.favorites.FavoritesRoute
import com.example.navegacioninferiormultiplaforma.ui.profile.ProfileRoute

private val navigationConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(AppDestination.Home.serializer())
            subclass(AppDestination.Favorites.serializer())
            subclass(AppDestination.Profile.serializer())
        }
    }
}

@Composable
fun AppNavigation() {

    // Pilas independientes para cada pestaña.
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

    // Pestaña seleccionada.
    var selectedTab by rememberSaveable {
        mutableStateOf("home")
    }

    // Pila activa.
    val currentBackStack = when (selectedTab) {
        "favorites" -> favoritesBackStack
        "profile" -> profileBackStack
        else -> homeBackStack
    }

    Scaffold(
        bottomBar = {
            NavigationBar {

                NavigationBarItem(
                    selected = selectedTab == "home",
                    onClick = { selectedTab = "home" },
                    icon = { Text("⌂") },
                    label = { Text("Inicio") }
                )

                NavigationBarItem(
                    selected = selectedTab == "favorites",
                    onClick = { selectedTab = "favorites" },
                    icon = { Text("♥") },
                    label = { Text("Favoritos") }
                )

                NavigationBarItem(
                    selected = selectedTab == "profile",
                    onClick = { selectedTab = "profile" },
                    icon = { Text("●") },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { innerPadding ->

        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = currentBackStack,
            onBack = {
                if (currentBackStack.size > 1) {
                    currentBackStack.removeLastOrNull()
                } else {
                    selectedTab = "home"
                }
            },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {

                entry<AppDestination.Home> {
                    HomeRoute()
                }

                entry<AppDestination.Favorites> {
                    FavoritesRoute()
                }

                entry<AppDestination.Profile> {
                    ProfileRoute()
                }
            }
        )
    }
}
