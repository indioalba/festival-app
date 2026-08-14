package com.indioalba.festival

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.indioalba.festival.ui.theme.FestivalTheme
import com.indioalba.festival.util.ConnectivityObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val status by connectivityObserver.observe().collectAsState(
                initial = ConnectivityObserver.Status.Unavailable
            )

            FestivalTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (status != ConnectivityObserver.Status.Available) {
                            OfflineIndicator()
                        }
                    }
                ) { innerPadding ->
                    Greeting(
                        name = "Festival",
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun OfflineIndicator() {
    Text(
        text = "Offline Mode",
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
            .padding(8.dp),
        color = Color.White,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.labelMedium
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FestivalTheme {
        Greeting("Festival")
    }
}
