package com.example.navegacioninferiormultiplaforma.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeRoute(
    viewModel: HomeViewModel = viewModel()
) {

    val uiState by
    viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onIncrement = viewModel::incrementCounter
    )
}


@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onIncrement: () -> Unit
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
            text = "Contador: ${uiState.counter}"
        )

        Button(
            onClick = onIncrement
        ) {

            Text(
                text = "Incrementar"
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState(),
        onIncrement = {}
    )
}