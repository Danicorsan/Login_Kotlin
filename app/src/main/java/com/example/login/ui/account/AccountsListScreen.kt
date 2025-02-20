package com.example.login.ui.account

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.login.base.Actions
import com.example.login.base.BaseTopAppBar
import com.example.login.base.BaseTopAppBarState
import com.example.login.base.Icons.SortAlphaDown
import com.example.login.base.Icons.SortAlphaDownAlt
import com.example.login.base.LoadingScreen
import com.example.login.base.NoDataScreen
import com.example.login.data.model.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class AccountsListEvents(
    val openDrawer: () -> Unit,
    val goToCreation: () -> Unit,
    val goToDetail: (Account) -> Unit,
    val goToDelete: (Account) -> Unit,
    val clickSort: () -> Unit,
)

@Composable
fun AccountsListScreen(
    viewModel: AccountsListsViewModel = hiltViewModel(),
    openDrawer: () -> Unit,
    goToCreation: () -> Unit,
    goToDetail: (Account) -> Unit,
) {
    // Cargar la lista cuando se inicia la pantalla
    LaunchedEffect(Unit) {
        viewModel.getList()
    }

    val events = AccountsListEvents(
        openDrawer = openDrawer,
        goToCreation = goToCreation,
        goToDetail = goToDetail,
        goToDelete = { account -> viewModel.delete(account) },
        clickSort = { viewModel.ordenar() }
    )
    Scaffold(
        topBar = { BaseTopAppBar(appBarState(events,viewModel.ordenadoAZ.value)) },
        floatingActionButton = {
            FloatingActionButton(onClick = goToCreation) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Añadir cuenta")
            }
        },
        content = { innerPadding ->

            val state = viewModel.state.collectAsState()

            when (val currentState = state.value) {
                is AccountsListState.Success -> {
                    if (currentState.accounts.isNotEmpty()) {
                        AccountsListContent(
                            accounts = currentState.accounts,
                            modifier = Modifier.padding(innerPadding),
                            events = events
                        )
                    } else {
                        NoDataScreen()
                    }
                }

                AccountsListState.Loading -> LoadingScreen()
                AccountsListState.NoData -> NoDataScreen()
            }
        }
    )


}

@Composable
fun AccountsListContent(
    accounts: List<Account>,
    modifier: Modifier,
    events: AccountsListEvents
) {
    var accountToDelete by remember { mutableStateOf<Account?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    modifier = Modifier
                        .padding(16.dp),
                    snackbarData = data,
                    containerColor = Color.Black, // Fondo negro
                    contentColor = Color.White // Texto blanco
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(accounts) { account ->
                AccountItem(
                    account = account,
                    modifier = Modifier.padding(8.dp),
                    goToDelete = { selectedAccount ->
                        accountToDelete = selectedAccount
                    },
                    goToDetail = events.goToDetail,
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )
            }
        }
    }

    if (accountToDelete != null) {
        DeleteAccountDialog(
            account = accountToDelete!!,
            onConfirm = {
                events.goToDelete(accountToDelete!!)
                accountToDelete = null
            },
            onDismiss = { accountToDelete = null }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AccountItem(
    account: Account,
    modifier: Modifier,
    goToDelete: (Account) -> Unit,
    goToDetail: (Account) -> Unit,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(5.dp)
            .combinedClickable(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Cuenta: ${account.name} - ${account.email}",
                            duration = SnackbarDuration.Short
                        )
                    }
                },
                onLongClick = {
                    goToDelete(account)
                }
            ),
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


/**
 * Configuración de la AppBar
 */
@Composable
fun appBarState(events: AccountsListEvents, sorted: Boolean): BaseTopAppBarState {
    return BaseTopAppBarState(
        title = "Cuentas",
        iconUpAction = Icons.Filled.Menu,
        upAction = { events.openDrawer() },
        actions = listOf(
            Actions(
                title = "Buscar",
                icon = Icons.Filled.Search,
                contentDescription = "Buscar cuenta",
                onClick = {},
                isVisible = true
            ),
            Actions(
                title = "Ordenar",
                icon = if (sorted) SortAlphaDown() else SortAlphaDownAlt(),
                contentDescription = "Filtrar cuentas",
                onClick = {events.clickSort()},
                isVisible = true
            )
        )
    )
}

@Composable
fun DeleteAccountDialog(
    account: Account,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Eliminar cuenta") },
        text = { Text(text = "¿Está seguro de que desea eliminar la cuenta: ${account.name}?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancelar")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() {
    AccountsListScreen(
        viewModel = hiltViewModel(),
        goToCreation = {},
        goToDetail = {},
        openDrawer = {}
    )
}
