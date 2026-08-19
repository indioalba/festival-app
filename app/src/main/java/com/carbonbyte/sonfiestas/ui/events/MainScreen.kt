package com.carbonbyte.sonfiestas.ui.events

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Church
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.SportsBasketball
import androidx.compose.material.icons.filled.Stadium
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.carbonbyte.sonfiestas.R
import com.carbonbyte.sonfiestas.data.model.Event
import com.carbonbyte.sonfiestas.data.model.EventCategory
import java.text.SimpleDateFormat
import java.util.Locale

private val VibrantTabColors = listOf(
    Color(0xFFAD1457), // Pink 800
    Color(0xFFD84315), // Deep Orange 800
    Color(0xFF1565C0), // Blue 800
    Color(0xFF4E342E),  // Brown 800
    Color(0xFF4527A0), // Deep Purple 800
    Color(0xFF283593), // Indigo 800
    Color(0xFF00695C) // Teal 800
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(uiState: EventsUiState, onIntent: (EventsIntent) -> Unit) {
    val pagerState = rememberPagerState(pageCount = { uiState.days.size })

    LaunchedEffect(pagerState.currentPage) {
        if (uiState.selectedDayIndex != pagerState.currentPage) {
            onIntent(EventsIntent.SelectDay(pagerState.currentPage))
        }
    }

    LaunchedEffect(uiState.selectedDayIndex) {
        if (pagerState.currentPage != uiState.selectedDayIndex) {
            pagerState.animateScrollToPage(uiState.selectedDayIndex)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                if (uiState.isOffline) {
                    OfflineIndicator()
                }
                Box(modifier = Modifier.background(Color.White)) {
                    Image(
                        painter = painterResource(id = R.drawable.splash_fiestas_agosto2026),
                        contentDescription = null,
                        modifier = Modifier
                            .matchParentSize()
                            .alpha(0.3f),
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.BottomCenter
                    )
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
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent
                        )
                    )
                }
                if (uiState.days.isNotEmpty()) {
                    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
                    ScrollableTabRow(
                        selectedTabIndex = pagerState.currentPage,
                        modifier = Modifier.fillMaxWidth(),
                        containerColor = Color.Transparent,
                        edgePadding = 0.dp,
                        divider = {},
                        indicator = { tabPositions ->
                            if (pagerState.currentPage < tabPositions.size) {
                                TabRowDefaults.SecondaryIndicator(
                                    modifier = Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                                    height = 4.dp,
                                    color = Color.White
                                )
                            }
                        }
                    ) {
                        uiState.days.forEachIndexed { index, day ->
                            val isSelected = pagerState.currentPage == index
                            val baseColor = VibrantTabColors[index % VibrantTabColors.size]
                            val backgroundColor =
                                if (isSelected) baseColor else baseColor.copy(alpha = 0.5f)

                            Tab(
                                selected = isSelected,
                                onClick = { onIntent(EventsIntent.SelectDay(index)) },
                                modifier = Modifier
                                    .width(screenWidth / 3)
                                    .background(backgroundColor),
                                selectedContentColor = Color.White,
                                unselectedContentColor = Color.White.copy(alpha = 0.7f),
                                text = {
                                    Text(
                                        text = formatDateTab(day),
                                        color = Color.White,
                                        fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Bold,
                                        style = MaterialTheme.typography.labelLarge,
                                        maxLines = 1,
                                        softWrap = false
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.weight(0.25f)) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = event.time,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
            Icon(
                imageVector = getCategoryIcon(event.category),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(0.75f)) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            if (event.location != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        modifier = Modifier.size(12.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = event.location,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
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
                modifier = Modifier
                    .size(60.dp)
                    .padding(horizontal = 8.dp),
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
        EventCategory.BULLS.name -> Icons.Default.Stadium
        EventCategory.SPORTS.name -> Icons.Default.SportsBasketball
        EventCategory.KIDS.name -> Icons.Default.ChildCare
        EventCategory.RELIGIOUS.name -> Icons.Default.Church
        EventCategory.GASTRONOMY.name -> Icons.Default.Restaurant
        else -> Icons.Default.Event
    }
}

fun formatDateTab(dateString: String): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val formatter = SimpleDateFormat("d EEEE", Locale.forLanguageTag("es-ES"))
        val date = parser.parse(dateString)
        date?.let {
            val formatted = formatter.format(it)
            // Capitalize the first letter of each word (e.g., "22 Lunes")
            formatted.split(" ").joinToString(" ") { word ->
                word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            }
        } ?: dateString
    } catch (e: Exception) {
        dateString
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val uiState = EventsUiState(
        showSplash = false,
        days = listOf("2026-08-21", "2026-08-22", "2026-08-23", "2026-08-24"),
        selectedDayIndex = 1,
        events = listOf(
            Event(
                id = 1,
                title = "Event 1",
                date = "2026-08-22",
                time = "10:00 - 12:30 / 17:00 h - 22:00h",
                category = EventCategory.MUSIC.name
            ),
            Event(
                id = 2,
                title = "Event 2",
                date = "2026-08-22",
                time = "12:00",
                category = EventCategory.SPORTS.name
            )
        )
    )
    MaterialTheme {
        MainScreen(uiState = uiState, onIntent = {})
    }
}
