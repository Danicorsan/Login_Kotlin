package com.example.login.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.login.ui.account.AccountScreen
import com.example.login.ui.account.AccountsListsViewModel
import com.example.login.ui.login.LoginScreen
import com.example.login.ui.login.LoginViewModel
import com.example.login.ui.navigation.SignInUpGraph.EMAIL
import com.example.login.ui.navigation.SignInUpGraph.PASSWORD
import com.example.login.ui.registrar.RegisterViewModel
import com.example.login.ui.registrar.SignUpScreen

object SignInUpGraph {
    const val ROUTE = "signUp"
    const val EMAIL = "email"
    const val PASSWORD = "password"

    // Aquí definimos la ruta correctamente con los parámetros
    fun login() = "${ROUTE}/login?${EMAIL}={email}&${PASSWORD}={password}"
    fun register() = "register_screen"
}

fun NavGraphBuilder.signInUpGraph(
    navController: NavController
) {
    navigation(startDestination = SignInUpGraph.login(), route = SignInUpGraph.ROUTE) {
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

            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                email = email,
                password = password,
                onclickCrearCuenta = { navController.navigate(SignInUpGraph.register()) },
                onSucces = { navController.navigate(AccountGraph.accountList()) },
                viewModel = loginViewModel
            )
        }
}

private fun NavGraphBuilder.signUp(navController: NavController){
    composable(SignInUpGraph.register()) {
        val registerViewModel: RegisterViewModel = hiltViewModel()
        SignUpScreen(
            navController = navController,
            viewModel = registerViewModel,
            onRegister = { email, password ->
                // Aquí accedes a los parámetros email y password
                // Luego navegas a la pantalla de login, pasando esos parámetros en la URL
                navController.navigate(
                    SignInUpGraph.login()
                        .replace("{email}", email)
                        .replace("{password}", password)
                )
            }
        )
    }
}



