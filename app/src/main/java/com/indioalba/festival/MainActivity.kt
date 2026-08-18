package com.indioalba.festival

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.indioalba.festival.data.model.Event
import com.indioalba.festival.data.model.EventCategory
import com.indioalba.festival.ui.events.EventsIntent
import com.indioalba.festival.ui.events.EventsUiState
import com.indioalba.festival.ui.events.EventsViewModel
import com.indioalba.festival.ui.events.SplashScreen
import com.indioalba.festival.ui.theme.FestivalTheme
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Locale

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(uiState: EventsUiState, onIntent: (EventsIntent) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { uiState.days.size })

    LaunchedEffect(uiState.selectedDayIndex) {
        if (pagerState.currentPage != uiState.selectedDayIndex) {
            pagerState.animateScrollToPage(uiState.selectedDayIndex)
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { index ->
            if (uiState.selectedDayIndex != index) {
                onIntent(EventsIntent.SelectDay(index))
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                if (uiState.isOffline) {
                    OfflineIndicator()
                }
                CenterAlignedTopAppBar(
                    title = { Text("Fiestas Agosto '26", fontWeight = FontWeight.Bold) },
                    actions = {
                        IconButton(onClick = { onIntent(EventsIntent.ToggleFavoritesFilter) }) {
                            Icon(
                                imageVector = if (uiState.isFilteredByFavorites) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Filter Favorites",
                                tint = if (uiState.isFilteredByFavorites) Color.Red else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                )
                if (uiState.days.isNotEmpty()) {
                    TabRow(selectedTabIndex = uiState.selectedDayIndex) {
                        uiState.days.forEachIndexed { index, day ->
                            Tab(
                                selected = uiState.selectedDayIndex == index,
                                onClick = { onIntent(EventsIntent.SelectDay(index)) },
                                text = { Text(formatDateTab(day)) }
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(innerPadding)
        ) { pageIndex ->
            val selectedDay = uiState.days.getOrNull(pageIndex)
            val eventsForDay = uiState.events
                .filter { it.date == selectedDay }
                .filter { if (uiState.isFilteredByFavorites) it.isFavorite else true }
            
            EventList(
                events = eventsForDay,
                onToggleFavorite = { id -> onIntent(EventsIntent.ToggleFavorite(id)) }
            )
        }
    }
}

@Composable
fun EventList(
    events: List<Event>,
    onToggleFavorite: (Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(events, key = { it.id }) { event ->
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
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = getCategoryIcon(event.category),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = event.time, style = MaterialTheme.typography.labelMedium, color = Color.Gray)
            Text(text = event.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            if (event.location != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = event.location, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
            }
        }
        if (event.imageUrl != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(event.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(60.dp).padding(horizontal = 8.dp),
                contentScale = ContentScale.Crop
            )
        }
        IconButton(onClick = { onToggleFavorite(event.id) }) {
            Icon(
                imageVector = if (event.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = if (event.isFavorite) Color.Red else Color.Gray
            )
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

fun getCategoryIcon(category: String): ImageVector {
    return when (category) {
        EventCategory.MUSIC.name -> Icons.Default.MusicNote
        EventCategory.BULLS.name -> Icons.Default.Pets
        EventCategory.SPORTS.name -> Icons.Default.SportsBasketball
        EventCategory.KIDS.name -> Icons.Default.ChildCare
        EventCategory.RELIGIOUS.name -> Icons.Default.AccountBalance
        EventCategory.GASTRONOMY.name -> Icons.Default.Restaurant
        else -> Icons.Default.Event
    }
}

fun formatDateTab(dateString: String): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val formatter = SimpleDateFormat("EEEE d", Locale("es", "ES"))
        val date = parser.parse(dateString)
        date?.let { formatter.format(it).replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() } } ?: dateString
    } catch (e: Exception) {
        dateString
    }
}
