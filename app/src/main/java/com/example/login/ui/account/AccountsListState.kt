package com.example.login.ui.account

import com.example.login.data.model.Account

sealed class AccountsListState {
    object NoData : AccountsListState()
    object Loading : AccountsListState()
    data class Success(val accounts: List<Account>) : AccountsListState()
}

