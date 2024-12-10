package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.login.ui.account.AccountsListsViewModel
import com.example.login.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = AccountsListsViewModel()

        setContent {
            val navController = rememberNavController()
            AppNavigation(navController = navController, viewModel = viewModel)
        }
    }
}
