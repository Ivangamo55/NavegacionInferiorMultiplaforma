package com.example.navegacioninferiormultiplaforma.navigation

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandler(enabled: Boolean = true, onBack: () -> Unit)
