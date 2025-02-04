package com.example.login.ui.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.login.base.LoadingScreen
import com.example.login.base.NoDataScreen
import com.example.login.data.model.Account

@Composable
fun AccountScreen(viewModel: AccountsListsViewModel = hiltViewModel()) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountListContent(accounts: List<Account>) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Cuentas") })
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding)
            ) {
                items(accounts) { account ->
                    AccountItem(account = account, Modifier.padding(8.dp))
                }
            }
        }
    )
}

@Composable
fun AccountItem(account: Account, modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = account.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = account.email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() {
    AccountScreen(viewModel = hiltViewModel())
}
