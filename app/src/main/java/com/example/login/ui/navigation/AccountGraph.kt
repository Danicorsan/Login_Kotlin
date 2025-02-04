package com.example.login.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.login.ui.account.AccountScreen
import com.example.login.ui.account.AccountsListsViewModel

object AccountGraph {
    const val ROUTE = "Account"
    fun accountList() = "$ROUTE/SCREEN"
}

fun NavGraphBuilder.accountGraph(
    navController: NavController
) {
    navigation(startDestination = AccountGraph.accountList(), route = AccountGraph.ROUTE) {
        account(navController)
    }
}

private fun NavGraphBuilder.account(
    navController: NavController
){
    composable(AccountGraph.accountList()) {
        val accountViewModel: AccountsListsViewModel = hiltViewModel()
        AccountScreen(
            viewModel = accountViewModel
        )
    }
}
