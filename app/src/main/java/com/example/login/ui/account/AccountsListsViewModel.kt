package com.example.login.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.data.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsListsViewModel @Inject constructor(
    private val repository: AccountRepository // Inyectado por Hilt
) : ViewModel() {

    private val _state = MutableStateFlow<AccountsListState>(AccountsListState.NoData)
    val state: StateFlow<AccountsListState> get() = _state

    private var isUserLoggedIn = false

    init {
        fetchAccountsList()
    }

    private fun fetchAccountsList() {
        viewModelScope.launch {
            _state.value = AccountsListState.Loading
            try {
                repository.getAccounts().collectLatest { accounts ->
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
                val result = repository.login(email, password)
                isUserLoggedIn = result.isSuccess
                if (isUserLoggedIn) {
                    fetchAccountsList()
                } else {
                    _state.value = AccountsListState.NoData
                }
            } catch (e: Exception) {
                _state.value = AccountsListState.NoData
            }
        }
    }
}
