package com.carbonbyte.sonfiestas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.carbonbyte.sonfiestas.ui.events.EventsViewModel
import com.carbonbyte.sonfiestas.ui.events.MainScreen
import com.carbonbyte.sonfiestas.ui.events.SplashScreen
import com.carbonbyte.sonfiestas.ui.theme.FestivalTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: EventsViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsState()

            FestivalTheme {
                if (uiState.showSplash) {
                    SplashScreen()
                } else {
                    MainScreen(uiState, viewModel::onIntent)
                }
            }
        }
    }
}
