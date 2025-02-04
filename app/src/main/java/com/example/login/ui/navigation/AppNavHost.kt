package com.example.login.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.login.ui.login.LoginViewModel
import com.example.login.ui.registrar.RegisterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
) {
    val viewModel: MainViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = viewModel.state.startNavDestination()
    ) {
        accountGraph(navController = navController)
        signInUpGraph(navController)
    }
}

