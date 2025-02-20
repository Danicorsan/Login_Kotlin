package com.example.login.base

import androidx.compose.ui.graphics.vector.ImageVector

data class BaseTopAppBarState(
    val title:String,
    val iconUpAction:ImageVector,
    val upAction:()->Unit,
    val actions: List<Actions>
)

data class Actions(
    val title:String,
    val icon:ImageVector,
    val contentDescription:String,
    val onClick:()->Unit,
    val isVisible:Boolean
)

sealed class Action(
    val name:String,
    val onClick:()->Unit,
    val isVisible: Boolean
){
    /*
    class ActionVector(
        name:String,
        val icon:ImageVector,
        onClick: () -> Unit,
        isVisible: Boolean = true
    ):Action(name, onClick, isVisible)
    */
}