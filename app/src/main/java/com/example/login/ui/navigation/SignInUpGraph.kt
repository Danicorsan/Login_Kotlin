package com.example.login.ui.navigation

import android.content.res.Resources
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.login.data.repository.AccountRepositoryDB
import com.example.login.ui.login.LoginScreen
import com.example.login.ui.login.LoginViewModel
import com.example.login.ui.navigation.SignInUpGraph.EMAIL
import com.example.login.ui.navigation.SignInUpGraph.PASSWORD
import com.example.login.ui.navigation.SignInUpGraph.ROUTE
import com.example.login.ui.registrar.RegisterViewModel
import com.example.login.ui.registrar.SignUpScreen

object SignInUpGraph {
    const val ROUTE = "signUp"
    const val EMAIL = "email"
    const val PASSWORD = "password"

    // Aquí definimos la ruta correctamente con los parámetros
    //fun login(email:String = "", password:String = "") = "${ROUTE}/login?${EMAIL}=$email&${PASSWORD}=$password"
    fun login() = "${ROUTE}/login?${EMAIL}={email}&${PASSWORD}={password}"//Ruta dinamica
    fun register() = "register_screen"
}

fun NavGraphBuilder.signInUpGraph(
    navController: NavController
) {
    navigation(startDestination = SignInUpGraph.login(), route = ROUTE) {
        login(navController)
        signUp(navController)
    }
}


private fun NavGraphBuilder.login(navController: NavController){
    composable(
        SignInUpGraph.login(),
        arguments = listOf(
            navArgument(EMAIL){
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument(PASSWORD){
                type = NavType.StringType
                defaultValue = ""
            },
        )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString(EMAIL) ?: ""
            val password = backStackEntry.arguments?.getString(PASSWORD) ?: ""

        LoginScreen(
            email = email,
            password = password,
            onClickCrearCuenta = { navController.navigate(SignInUpGraph.register()) },
            viewModel = hiltViewModel(),
            onSuccess = {
                navController.navigate(AccountGraph.ROUTE) {
                    popUpTo(ROUTE) { inclusive = true } // Borra la pila de login
                }
            },
        )
        }
}
private fun NavGraphBuilder.signUp(navController: NavController) {
    composable(SignInUpGraph.register()) {
        SignUpScreen(
            viewModel = hiltViewModel(),
            onRegisterSuccess = { email, password ->
                navController.navigate(
                    "$ROUTE/login?$EMAIL=$email&$PASSWORD=$password"
                )
            },
            onNavigateToLogin = { navController.navigate(ROUTE)}
        )
    }
}



