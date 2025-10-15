package app.batteryfy.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import app.batteryfy.mobile.ui.theme.BatteryFyTheme

import androidx.compose.material3.Surface
import androidx.compose.ui.tooling.preview.Preview

import app.batteryfy.mobile.navigation.AppNavigation
import app.batteryfy.mobile.ui.theme.BatteryFyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Sets up edge-to-edge display
        setContent {
            BatteryFyTheme {
                Surface {
                    AppNavigation()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BatteryFyTheme {
        AppNavigation()
    }
}