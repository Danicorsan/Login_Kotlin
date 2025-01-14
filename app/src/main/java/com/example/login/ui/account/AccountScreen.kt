package com.example.login.ui.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.login.base.LoadingScreen
import com.example.login.base.NoDataScreen
import com.example.login.data.model.Account

@Composable
fun AccountScreen(viewModel: AccountsListsViewModel = hiltViewModel() ) {
    val state = viewModel.state.collectAsState()

    when (val currentState = state.value) {
        is AccountsListState.Success -> {
            if (currentState.accounts.isNotEmpty()) {
                AccountListContent(accounts = currentState.accounts)
            }
        }
        AccountsListState.Loading -> LoadingScreen()
        AccountsListState.NoData -> NoDataScreen()
    }
}

@Composable
fun AccountListContent(accounts: List<Account>) {
    LazyColumn {
        items(accounts) { account ->
            AccountItem(account = account)
        }
    }
}
@Composable
fun AccountItem(account: Account) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = account.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = account.email,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

