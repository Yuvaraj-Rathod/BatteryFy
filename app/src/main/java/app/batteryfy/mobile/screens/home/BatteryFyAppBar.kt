package app.batteryfy.mobile.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.batteryfy.mobile.ui.theme.BatteryFyTheme
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BatteryFyAppBar(
    onMenuClick: () -> Unit,
    onRefreshClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            ElevatedIcon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                onClick = onMenuClick
            )
        },
        title = {
            Text(
                text = "BatteryFy",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.tertiary
                        )
                    )
                ),
                modifier = Modifier.fillMaxWidth(), // Added to ensure centering within the title slot
                textAlign = TextAlign.Center
            )
        },
        actions = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ElevatedIcon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh",
                    onClick = onRefreshClick
                )
                // Spacer width updated to match reference
                Spacer(modifier = Modifier.width(13.dp))
                ElevatedIcon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    onClick = onProfileClick
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.9f)
        ),
        // Padding updated to match reference
        modifier = Modifier.padding(horizontal = 2.dp)
    )
}

@Composable
fun ElevatedIcon(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    // This structure now exactly matches your reference for a consistent look.
    Box(
        modifier = Modifier
            .size(40.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp), // Shape updated to 8.dp
                clip = false
            )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(8.dp), // Shape updated to 8.dp
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            IconButton(
                onClick = onClick,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = contentDescription,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(25.dp) // Icon size updated to 28.dp
                )
            }
        }
    }
}


// --- Previews ---

@Preview(name = "App Bar Light")
@Composable
private fun BatteryFyAppBarLightPreview() {
    BatteryFyTheme(darkTheme = false) {
        Surface {
            BatteryFyAppBar(onMenuClick = {}, onRefreshClick = {}, onProfileClick = {})
        }
    }
}

@Preview(name = "App Bar Dark")
@Composable
private fun BatteryFyAppBarDarkPreview() {
    BatteryFyTheme(darkTheme = true) {
        Surface {
            BatteryFyAppBar(onMenuClick = {}, onRefreshClick = {}, onProfileClick = {})
        }
    }
}