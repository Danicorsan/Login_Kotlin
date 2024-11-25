package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.login.registrar.SignUpScreen
import com.example.login.ui.theme.LoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "login_screen") {
                    composable("login_screen") {
                        LoginScreen(navController = navController) // Pasamos navController a LoginScreen
                    }
                    composable("sign_up_screen") {
                        SignUpScreen() // Pantalla de registro
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}
