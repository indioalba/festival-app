package com.indioalba.festival

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.indioalba.festival.data.model.Event
import com.indioalba.festival.ui.events.EventsIntent
import com.indioalba.festival.ui.events.EventsViewModel
import com.indioalba.festival.ui.theme.FestivalTheme
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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (uiState.isOffline) {
                            OfflineIndicator()
                        }
                    },
                ) { innerPadding ->
                    EventList(
                        events = uiState.events,
                        modifier = Modifier.padding(innerPadding),
                        onToggleFavorite = { id ->
                            viewModel.onIntent(EventsIntent.ToggleFavorite(id))
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun EventList(
    events: List<Event>,
    modifier: Modifier = Modifier,
    onToggleFavorite: (Int) -> Unit = {},
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(events) { event ->
            EventItem(event, onToggleFavorite)
            HorizontalDivider()
        }
    }
}

@Composable
fun EventItem(event: Event, onToggleFavorite: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleFavorite(event.id) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = event.title, style = MaterialTheme.typography.titleMedium)
            Text(text = "${event.date} - ${event.time}", style = MaterialTheme.typography.bodySmall)
            Text(text = event.category, style = MaterialTheme.typography.labelSmall)
        }
        Icon(
            imageVector = if (event.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Favorite",
            tint = if (event.isFavorite) Color.Red else Color.Gray,
        )
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
        style = MaterialTheme.typography.labelMedium,
    )
}

@Preview(showBackground = true)
@Composable
fun EventListPreview() {
    FestivalTheme {
        EventList(
            events = listOf(
                Event(title = "Test Event", date = "2025-10-11", time = "10:00", category = "OTHER"),
            ),
        )
    }
}
