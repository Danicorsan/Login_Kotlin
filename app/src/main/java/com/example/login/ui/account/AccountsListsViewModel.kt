package com.example.login.ui.account

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.data.model.Account
import com.example.login.data.network.BaseResult
import com.example.login.data.repository.AccountRepositoryDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsListsViewModel @Inject constructor(
    //Private val repository:AccountRepository ES EL ANTIGUO
    private val repository: AccountRepositoryDB // Inyectado por Hilt
) : ViewModel() {

    private val _state = MutableStateFlow<AccountsListState>(AccountsListState.NoData)
    val state: StateFlow<AccountsListState> get() = _state

    var dataset by mutableStateOf(emptyList<Account>())

    private var isUserLoggedIn = false

    //Bandera para ordenar con mutableStateof para que sea observado
    var ordenadoAZ: MutableState<Boolean> = mutableStateOf(false)
    //var ordenadoAZ by mutableStateOf<Boolean>(false)

    // Lista temporal para almacenar las cuentas
    private var currentAccounts: List<Account> = emptyList()

    fun getList() {
        viewModelScope.launch {
            _state.value = AccountsListState.Loading
            try {
                repository.getData().collectLatest { accounts ->
                    currentAccounts = accounts // Almacenar la lista actual
                    _state.value = if (accounts.isNotEmpty()) {
                        AccountsListState.Success(accounts)
                    } else {
                        AccountsListState.NoData
                    }
                }
            } catch (e: Exception) {
                _state.value = AccountsListState.NoData
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = repository.validate(email, password)
                if (result is BaseResult.Success) {
                    getList()
                } else {
                    _state.value = AccountsListState.NoData
                }
            } catch (e: Exception) {
                _state.value = AccountsListState.NoData
            }
        }
    }

    fun delete(account: Account) {
        viewModelScope.launch {
            repository.deleteAccount(account)
            getList() // Volver a cargar la lista después de eliminar
        }
    }

    fun ordenar(){
        if (!ordenadoAZ.value) {
            ordenadoAZ.value = true
            val sortedAccounts = currentAccounts.sortedBy { it.name }
            _state.value = AccountsListState.Success(sortedAccounts)
        } else {
            ordenadoAZ.value = false
            val sortedAccounts = currentAccounts.sortedByDescending { it.name }
            _state.value = AccountsListState.Success(sortedAccounts)
        }
    }
}