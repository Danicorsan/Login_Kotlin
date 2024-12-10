package com.example.login.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.data.model.Account
import com.example.login.data.repository.AccountRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AccountsListsViewModel : ViewModel() {
    private val _state = MutableStateFlow<AccountsListState>(AccountsListState.NoData)
    val state: StateFlow<AccountsListState> get() = _state

    private var isUserLoggedIn = false

    fun isLoggedIn(): Boolean = isUserLoggedIn

    fun getList() {
        viewModelScope.launch {
            _state.value = AccountsListState.Loading
            AccountRepository.getAccounts().collect { accounts ->
                if (accounts.isNotEmpty()) {
                    _state.value = AccountsListState.Success(accounts)
                } else {
                    _state.value = AccountsListState.NoData
                }
            }
        }
    }

    fun login(email: String, password: String): Boolean {
        val result = AccountRepository.login(email, password)
        isUserLoggedIn = result.isSuccess
        if (isUserLoggedIn) {
            getList()
        }
        return isUserLoggedIn
    }
}
