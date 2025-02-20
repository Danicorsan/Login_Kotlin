package com.example.login.base

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopAppBar(appBarState: BaseTopAppBarState) {
    var menuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        colors = TopAppBarColors(
            containerColor = Color.Gray,
            actionIconContentColor = Color.Black,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.Red,
            scrolledContainerColor = Color.White
        ),
        title = {
            Text(text = appBarState.title)
        },
        navigationIcon = {
            IconButton(onClick = appBarState.upAction) {
                Icon(imageVector = appBarState.iconUpAction, contentDescription = "Menú")
            }
        },
        actions = {
            appBarState.actions.filter { it.isVisible }.forEach { action ->
                IconButton(onClick = action.onClick) {
                    Icon(imageVector = action.icon, contentDescription = action.contentDescription)
                }
            }

            val hiddenActions = appBarState.actions.filter { !it.isVisible }
            if (hiddenActions.isNotEmpty()) {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Más opciones")
                }

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    hiddenActions.forEach { action ->
                        DropdownMenuItem(
                            text = { Text(action.title) },
                            onClick = {
                                menuExpanded = false
                                action.onClick()
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = action.icon,
                                    contentDescription = action.contentDescription
                                )
                            }
                        )
                    }
                }
            }
        }
    )
}

