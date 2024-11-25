package com.example.login.base

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CampoForm(
    value: String,
    isError: Boolean,
    texto: String,
    onValueChange: (String) -> Unit,
    errorMessage: String = "Formato Incorrecto"
) {
    TextField(
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
        isError = isError,
        label = { Text(texto) },
        supportingText = {
            Row {
                Text(if (isError) "$errorMessage: " else "")
                Spacer(Modifier.weight(1f))
            }
        },
    )
}