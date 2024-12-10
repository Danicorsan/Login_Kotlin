package com.example.login.ui.registrar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.base.CampoFormulario

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit, // Navegación al login
    navController: NavController
) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Icono de Android
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Android Icon",
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Crea tu cuenta", style = MaterialTheme.typography.headlineSmall)

        // Campos de formulario
        Spacer(modifier = Modifier.height(24.dp))
        CampoFormulario(value = nombre, onValueChange = { nombre = it }, isError = false, texto = "Nombre")
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(value = apellidos, onValueChange = { apellidos = it }, isError = false, texto = "Apellidos")
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(value = email, onValueChange = { email = it }, isError = false, texto = "Correo")
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(value = password, onValueChange = { password = it }, isError = false, texto = "Contraseña")

        // Botón de registro
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Lógica para registrar (puedes agregar lógica de validación aquí)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrarse")
        }

        // Botón para ir al Login
        Spacer(modifier = Modifier.height(16.dp))
        Divider() // Línea separadora
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("¿Ya tienes cuenta?")
            TextButton(onClick = onNavigateToLogin) {
                Text("Inicia sesión")
            }
        }
    }
}
