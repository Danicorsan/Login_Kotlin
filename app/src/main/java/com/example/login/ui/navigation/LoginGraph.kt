package com.example.login.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.login.ui.account.AccountScreen
import com.example.login.ui.account.AccountsListsViewModel
import com.example.login.ui.login.LoginScreen
import com.example.login.ui.login.LoginViewModel
import com.example.login.ui.registrar.RegisterViewModel
import com.example.login.ui.registrar.SignUpScreen

object LoginGraph {
    const val ROUTE = "login"
    const val LOGIN_ROUTE = "login_screen"
    const val REGISTER_ROUTE = "register_screen"
    const val ACCOUNT_ROUTE = "account_screen"
}

fun NavGraphBuilder.loginGraph(
    navController: NavController
) {
    navigation(startDestination = LoginGraph.LOGIN_ROUTE, route = LoginGraph.ROUTE) {
        composable(LoginGraph.LOGIN_ROUTE) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                onclickCrearCuenta = { navController.navigate(LoginGraph.REGISTER_ROUTE) },
                onSucces = { navController.navigate(LoginGraph.ACCOUNT_ROUTE) },
                viewModel = loginViewModel
            )
        }
        composable(LoginGraph.REGISTER_ROUTE) {
            val registerViewModel: RegisterViewModel = hiltViewModel()
            SignUpScreen(
                navController = navController,
                viewModel = registerViewModel
            )
        }
        composable(LoginGraph.ACCOUNT_ROUTE) {
            val accountViewModel: AccountsListsViewModel = hiltViewModel()
            AccountScreen(
                viewModel = accountViewModel
            )
        }
    }
}
