package com.example.navegacioninferiormultiplaforma.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ProfileRoute(viewModel: ProfileViewModel = viewModel()) {
val iuState by  viewModel.uiState.collectAsStateWithLifecycle()

    ProfileScreen(uiState = iuState)
}

@Composable
fun ProfileScreen(
    uiState: ProfileUiState = ProfileUiState()
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Perfil"
        )

        Text(
            text = uiState.name
        )

        Text(
            text = uiState.profile
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        uiState = ProfileUiState()
    )
}