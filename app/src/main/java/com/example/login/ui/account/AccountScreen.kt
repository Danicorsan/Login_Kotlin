package com.example.login.ui.account

import android.content.ClipData
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.login.base.LoadingScreen
import com.example.login.base.NoDataScreen
import com.example.login.data.model.Account

@Composable
fun AccountScreen(viewModel: AccountsListsViewModel, navController: NavHostController) {
    val state = viewModel.state.collectAsState()

    when (val currentState = state.value) {
        is AccountsListState.Success -> AccountListContent(accounts = currentState.accounts)
        AccountsListState.Loading -> LoadingScreen()
        AccountsListState.NoData -> NoDataScreen()
    }
}

@Composable
fun AccountListContent(accounts: List<Account>) {
    LazyColumn {
        items(accounts) { account ->
            AccountItem(account = account) // Renderiza cada cuenta con AccountItem
        }
    }
}
@Composable
fun AccountItem(account: Account) {
    androidx.compose.material3.Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = androidx.compose.material3.MaterialTheme.shapes.medium,
        tonalElevation = 4.dp
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            androidx.compose.foundation.layout.Column(
                modifier = Modifier.weight(1f)
            ) {
                androidx.compose.material3.Text(
                    text = account.name,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                )
                androidx.compose.material3.Text(
                    text = account.email,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
