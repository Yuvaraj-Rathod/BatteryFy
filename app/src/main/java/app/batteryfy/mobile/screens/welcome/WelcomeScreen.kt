package app.batteryfy.mobile.screens.welcome


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.batteryfy.mobile.ui.theme.BatteryFyTheme
import com.airbnb.lottie.compose.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun WelcomeScreen(
    onGetStartedClicked: () -> Unit,
    onSignInClicked: () -> Unit
) {

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {

            // --- Main Content ---
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Reduced top weight to bring content up
                Spacer(modifier = Modifier.height(30.dp))

                // --- App Name with Gradient ---
                val gradientBrush = Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.tertiary
                    )
                )

                Text(
                    text = "BatteryFy",
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Bold,
                        brush = gradientBrush // Applying the gradient here
                    )
                )
                Spacer(modifier = Modifier.height(70.dp))
                // Lottie Animation
                val composition by rememberLottieComposition(LottieCompositionSpec.Asset("electriccar.json"))
                val progress by animateLottieCompositionAsState(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier.size(330.dp) // Slightly reduced size for better fit
                )

                // Balanced the bottom weight
                Spacer(modifier = Modifier.weight(1.0f))

                // Tagline
                Text(
                    text = "The future of EV charging is here. Power up your journey, seamlessly.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Get Started Button
                Button(
                    onClick = onGetStartedClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = "Get Started",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sign In Link
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { onSignInClicked() }
                ) {
                    Text(
                        "Already have an account? ",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "Login",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Spacer(modifier = Modifier.height(48.dp)) // Bottom padding
            }
        }
    }
}

// --- Previews ---

@Preview(name = "Welcome Screen Light")
@Composable
fun WelcomeScreenLightPreview() {
    BatteryFyTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            WelcomeScreen(
                onGetStartedClicked = {},
                onSignInClicked = { TODO() }
            )
        }
    }
}

@Preview(name = "Welcome Screen Dark")
@Composable
fun WelcomeScreenDarkPreview() {
    BatteryFyTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            WelcomeScreen(
                onGetStartedClicked = {},
                onSignInClicked = { TODO() }
            )
        }
    }
}
