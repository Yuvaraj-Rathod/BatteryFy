package app.batteryfy.mobile.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.batteryfy.mobile.model.EVService
import app.batteryfy.mobile.ui.theme.BatteryFyTheme

@Composable
fun EVServiceCard(service: EVService, onBookClick: () -> Unit) {
    Card(
        // The fillMaxWidth from your reference wouldn't work well in a LazyRow.
        // Using a fixed width similar to the Station card for consistency.
        modifier = Modifier.width(280.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = service.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = service.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onBookClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Book Service", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}


// --- Previews ---

@Preview(name = "Service Card Light")
@Composable
private fun EVServiceCardLightPreview() {
    val sampleService = EVService("1", "Premium Cleaning", "Get your EV detailed and cleaned by professionals.")
    BatteryFyTheme(darkTheme = false) {
        Surface {
            EVServiceCard(service = sampleService, onBookClick = {})
        }
    }
}

@Preview(name = "Service Card Dark")
@Composable
private fun EVServiceCardDarkPreview() {
    val sampleService = EVService("1", "Premium Cleaning", "Get your EV detailed and cleaned by professionals.")
    BatteryFyTheme(darkTheme = true) {
        Surface {
            EVServiceCard(service = sampleService, onBookClick = {})
        }
    }
}