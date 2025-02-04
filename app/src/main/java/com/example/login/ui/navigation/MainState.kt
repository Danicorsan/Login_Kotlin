package com.example.login.ui.navigation

data class MainState(
    val activeAccount:Boolean
){
    fun startNavDestination() = if (activeAccount)
        AccountGraph.ROUTE
    else
        SignInUpGraph.ROUTE
}