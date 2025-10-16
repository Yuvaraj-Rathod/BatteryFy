package app.batteryfy.mobile.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.batteryfy.mobile.R
import app.batteryfy.mobile.model.Amenity
import app.batteryfy.mobile.model.EVService
import app.batteryfy.mobile.model.Station
import app.batteryfy.mobile.ui.theme.BatteryFyTheme


// --- Main HomeScreen Composable ---
@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    stations: List<Station>,
    amenities: List<Amenity>,
    services: List<EVService>,             // <-- It's declared here!
    onMenuClick: () -> Unit,
    onRefreshClick: () -> Unit,
    onProfileClick: () -> Unit,
    onFabClick: () -> Unit,
    onBookStationClick: (String) -> Unit,
    onViewAmenityClick: (String) -> Unit,
    onBookServiceClick: (String) -> Unit, // <-- It's declared here!
) {
    // Handle back press to exit the app from the home screen
    val activity = LocalContext.current as? Activity
    BackHandler {
        activity?.finish()
    }

    Scaffold(
        topBar = {
            BatteryFyAppBar(
                onMenuClick = onMenuClick,
                onRefreshClick = onRefreshClick,
                onProfileClick = onProfileClick
            )
        },
        floatingActionButton = {
            GradientFab(
                onClick = onFabClick,
                icon = Icons.Filled.Directions,
                contentDescription = "Open Map"
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- Charging Stations Section ---
            item { SectionTitle(title = "Nearby Stations") }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 8.dp) // Prevents card shadow from being cut
                ) {
                    items(stations) { station ->
                        ChargingStationCard(
                            station = station,
                            onBookClick = { onBookStationClick(station.id) }
                        )
                    }
                }
            }

            // --- EV Services Section ---
            item { SectionTitle(title = "EV Services") }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 8.dp)
                ) {
                    items(services) { service ->
                        EVServiceCard(
                            service = service,
                            onBookClick = { onBookServiceClick(service.id) }
                        )
                    }
                }
            }

            // --- Amenities/Zones Section ---
            item { SectionTitle(title = "Recharge & Relax") }
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 8.dp)
                ) {
                    items(amenities) { amenity ->
                        AmenityCard(
                            amenity = amenity,
                            onViewClick = { onViewAmenityClick(amenity.id) }
                        )
                    }
                }
            }
            // Spacer for the FAB
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }
}

// --- Reusable UI Components ---
@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
fun GradientFab(
    onClick: () -> Unit,
    icon: ImageVector,
    contentDescription: String
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.tertiary
                    )
                )
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.size(28.dp)
        )
    }
}

// --- Preview ---


@Preview(name = "Home Screen Dark", showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val sampleStations = listOf(
        Station("1", "EcoCharge Hub", "Available", "150 kW", "0 min", "₹18"),
        Station("2", "PowerUp Point", "Occupied", "50 kW", "25 min", "₹15"),
        Station("3", "Volt-In", "Available", "22 kW", "0 min", "₹12")
    )
    val sampleAmenities = listOf(
        Amenity("a1", "Domino's Pizza", "Enjoy a fresh pizza while you wait.", R.drawable.dominos),
        Amenity("a2", "Gamer's Paradise", "Play the latest arcade games.", R.drawable.gamingzone)
    )
    val sampleServices = listOf(
        EVService("s1", "Maintenance", "Schedule routine checkups."),
        EVService("s2", "Cleaning", "Premium detailing services.")
    )

    BatteryFyTheme(darkTheme = true) {
        HomeScreen(
            stations = sampleStations,
            amenities = sampleAmenities,
            services = sampleServices,
            onMenuClick = {}, onRefreshClick = {}, onProfileClick = {}, onFabClick = {},
            onBookStationClick = {}, onViewAmenityClick = {}, onBookServiceClick = {}
        )
    }
}


@Preview(name = "Home Screen Light", showBackground = true)
@Composable
private fun HomeScreenLightPreview() {
    val sampleStations = listOf(
        Station("1", "EcoCharge Hub", "Available", "150 kW", "0 min", "₹18"),
        Station("2", "PowerUp Point", "Occupied", "50 kW", "25 min", "₹15"),
        Station("3", "Volt-In", "Available", "22 kW", "0 min", "₹12")
    )
    val sampleAmenities = listOf(
        Amenity("a1", "Domino's Pizza", "Enjoy a fresh pizza while you wait.", R.drawable.dominos),
        Amenity("a2", "Gamer's Paradise", "Play the latest arcade games.", R.drawable.gamingzone)
    )
    val sampleServices = listOf(
        EVService("s1", "Maintenance", "Schedule routine checkups."),
        EVService("s2", "Cleaning", "Premium detailing services.")
    )

    BatteryFyTheme(darkTheme = false) {
        HomeScreen(
            stations = sampleStations,
            amenities = sampleAmenities,
            services = sampleServices,
            onMenuClick = {}, onRefreshClick = {}, onProfileClick = {}, onFabClick = {},
            onBookStationClick = {}, onViewAmenityClick = {}, onBookServiceClick = {}
        )
    }
}