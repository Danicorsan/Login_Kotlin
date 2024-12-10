package com.example.login.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

    @Composable
    fun Offline(modifier: Modifier = Modifier)=OfflineUI(modifier)


    @Composable
    fun OfflineUI(modifier: Modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen de desconexión usando una imagen personalizada
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Sin conexión",
                tint = Color.Red,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(16.dp)) // Espacio entre la imagen y el texto

            // Texto indicando desconexión
            Text(
                text = "Sin conexión a internet",
                fontSize = 18.sp,
                color = Color.Gray
            )
        }
    }
