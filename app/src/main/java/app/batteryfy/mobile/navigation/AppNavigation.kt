package app.batteryfy.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.batteryfy.mobile.R
import app.batteryfy.mobile.model.Amenity
import app.batteryfy.mobile.model.EVService
import app.batteryfy.mobile.model.Station
import app.batteryfy.mobile.screens.auth.SignUpScreen
import app.batteryfy.mobile.screens.SplashScreen
import app.batteryfy.mobile.screens.auth.SignInScreen
import app.batteryfy.mobile.screens.home.HomeScreen
import app.batteryfy.mobile.screens.welcome.WelcomeScreen

// A sealed class to define our app's screen routes for type-safety
sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object SignIn : Screen("signin_screen")
    object SignUp : Screen("signup_screen")
    object Home : Screen("home_screen") // Added Home route
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onGetStartedClicked = {
                    navController.navigate(Screen.SignUp.route)
                },
                onSignInClicked = {
                    navController.navigate(Screen.SignIn.route)
                }
            )
        }

        composable(Screen.SignIn.route) {
            SignInScreen(
                onSignInClicked = { email, password ->
                    // TODO: Handle login logic here, then navigate to home
                    navController.navigate(Screen.Home.route) {
                        // Clear the auth flow from the back stack
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                },
                onSignUpClicked = {
                    navController.navigate(Screen.SignUp.route)
                }
            )
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(
                onSignUpClicked = { email, password, fullName ->
                    // TODO: Handle registration logic here, then navigate to home
                    navController.navigate(Screen.Home.route) {
                        // Clear the auth flow from the back stack
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                    }
                },
                onSignInClicked = {
                    navController.navigate(Screen.SignIn.route)
                }
            )
        }

        // --- Home Screen ---
        composable(Screen.Home.route) {
            // This is where you would get real data from a ViewModel
            val sampleStations = listOf(
                Station("1", "EcoCharge Hub", "Available", "150 kW", "0 min", "₹18"),
                Station("2", "PowerUp Point", "Occupied", "50 kW", "25 min", "₹15"),
                Station("3", "Volt-In", "Available", "22 kW", "0 min", "₹12")
            )
            val sampleAmenities = listOf(
                Amenity(
                    "a1",
                    "Domino's Pizza",
                    "Enjoy a fresh pizza while you wait.",
                    R.drawable.dominos
                ),
                Amenity("a2", "Gamer's Paradise", "Play the latest arcade games.", R.drawable.gamingzone)
            )
            val sampleServices = listOf(
                EVService("s1", "Maintenance", "Schedule routine checkups."),
                EVService("s2", "Cleaning", "Premium detailing services.")
            )

            HomeScreen(
                stations = sampleStations,
                amenities = sampleAmenities,
                services = sampleServices,
                onMenuClick = { /*TODO*/ },
                onRefreshClick = { /*TODO*/ },
                onProfileClick = { /*TODO*/ },
                onFabClick = { /*TODO*/ },
                onBookStationClick = { stationId -> /* TODO: Navigate to booking screen */ },
                onViewAmenityClick = { amenityId -> /* TODO: Navigate to amenity detail screen */ },
                onBookServiceClick = { serviceId -> /* TODO: Navigate to service screen */ }
            )
        }
    }
}