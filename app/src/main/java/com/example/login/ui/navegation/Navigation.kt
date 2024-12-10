package com.example.login.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.login.ui.login.LoginScreen
import com.example.login.ui.registrar.SignUpScreen
import com.example.login.ui.account.AccountScreen
import com.example.login.ui.account.AccountsListsViewModel

@Composable
fun AppNavigation(navController: NavHostController, viewModel: AccountsListsViewModel) {
    NavHost(navController = navController, startDestination = "login_screen") {
        composable("login_screen") {
            LoginScreen(navController = navController, viewModel = viewModel)
        }
        composable("register_screen") {
            SignUpScreen(onNavigateToLogin = { navController.navigate("login_screen") }, navController = navController)
        }
        composable("account_screen") {
            AccountScreen(viewModel = viewModel, navController = navController)
        }
    }
}
