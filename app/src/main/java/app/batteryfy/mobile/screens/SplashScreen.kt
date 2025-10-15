package app.batteryfy.mobile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.batteryfy.mobile.navigation.Screen
import app.batteryfy.mobile.ui.theme.BatteryFyTheme
import com.airbnb.lottie.compose.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    // This effect manages the system UI for a fully immersive experience
    DisposableEffect(systemUiController, useDarkIcons) {
        // Hide the system bars
        systemUiController.isSystemBarsVisible = false

        // onDispose will be called when the composable leaves the screen
        onDispose {
            systemUiController.isSystemBarsVisible = true
        }
    }

    // This effect handles the navigation logic
    LaunchedEffect(key1 = true) {
        delay(3000L) // Wait for 3 seconds
        navController.navigate(Screen.Welcome.route) {
            // Remove the splash screen from the back stack
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }

    // --- Gradient Background ---
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
            MaterialTheme.colorScheme.background
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush),
        contentAlignment = Alignment.Center
    ) {
        // You'll need to add a "carcharge.json" file to your assets folder
        val composition by rememberLottieComposition(LottieCompositionSpec.Asset("chargingelectricity.json"))
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(150.dp)
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    BatteryFyTheme {
        // In a real app, you'd pass a real NavController.
        // For a preview, we can't, so this won't navigate. 
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                            MaterialTheme.colorScheme.background
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text("Splash Screen with Animation", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}