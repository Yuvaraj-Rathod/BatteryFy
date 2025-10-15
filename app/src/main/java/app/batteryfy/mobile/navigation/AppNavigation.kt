package app.batteryfy.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.batteryfy.mobile.screens.auth.SignUpScreen
import app.batteryfy.mobile.screens.SplashScreen
import app.batteryfy.mobile.screens.auth.SignInScreen
import app.batteryfy.mobile.screens.welcome.WelcomeScreen

// A sealed class to define our app's screen routes for type-safety
sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
    object SignIn : Screen("signin_screen")
    object SignUp : Screen("signup_screen")
    object Splash : Screen("splash_screen")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route // The first screen the user sees
    ) {
        composable(route= Screen.Splash.route) {
            SplashScreen(navController)
        }
        // Welcome Screen
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

        // Sign In Screen
        composable(Screen.SignIn.route) {
            SignInScreen(
                onSignInClicked = { email, password ->
                    // TODO: Handle login logic here
                },
                onSignUpClicked = {
                    navController.navigate(Screen.SignUp.route)
                }
            )
        }

        // Sign Up Screen
        composable(Screen.SignUp.route) {
            SignUpScreen(
                onSignUpClicked = { email, password, fullName ->
                    // TODO: Handle registration logic here
                },
                onSignInClicked = {
                    navController.navigate(Screen.SignIn.route)
                }
            )
        }
    }
}