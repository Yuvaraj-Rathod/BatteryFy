package app.batteryfy.mobile.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

// Define the Dark Color Scheme based on our palette
private val BatteryFyDarkColorScheme = darkColorScheme(
    primary = ElectricGreen,
    onPrimary = RichBlack, // Black text on a bright green button for high contrast
    secondary = LightSlate,
    onSecondary = OffWhite,
    background = DeepCharcoal,
    onBackground = OffWhite,
    surface = DarkSlate, // Color for cards, sheets, and menus
    onSurface = OffWhite,
    error = StatusCrimsonRed,
    onError = PureWhite
)

// Define the Light Color Scheme based on our palette
private val BatteryFyLightColorScheme = lightColorScheme(
    primary = VibrantGreen,
    onPrimary = PureWhite, // White text on our slightly darker green
    secondary = LightAsh,
    onSecondary = RichBlack,
    background = LightGrey,
    onBackground = RichBlack,
    surface = PureWhite, // Cards and surfaces are pure white in light theme
    onSurface = RichBlack,
    error = StatusCrimsonRed,
    onError = PureWhite
)

@Composable
fun BatteryFyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> BatteryFyDarkColorScheme
        else -> BatteryFyLightColorScheme
    }

    val systemUiController = rememberSystemUiController()
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            // Set status bar and navigation bar colors
            systemUiController.setSystemBarsColor(
                color = colorScheme.background, // Match system bars to the app background
                darkIcons = !darkTheme // Use dark icons on light theme, light icons on dark theme
            )

            // This makes sure the content can draw behind the system bars.
            // It's key for a modern, edge-to-edge look.
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Assuming you have a Typography.kt file
        content = content
    )
}