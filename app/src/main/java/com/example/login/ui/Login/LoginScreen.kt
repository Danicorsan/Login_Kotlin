package com.example.login.ui.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.login.R
import com.example.login.base.CampoFormulario
import com.example.login.ui.account.AccountsListsViewModel

@Composable
fun LoginScreen(navController: NavHostController?, viewModel: AccountsListsViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

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
        Text("Bienvenido", style = MaterialTheme.typography.headlineSmall)

        // Campos de formulario
        Spacer(modifier = Modifier.height(24.dp))
        CampoFormulario(value = email, onValueChange = { email = it }, isError = false, texto = "Correo")
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(value = password, onValueChange = { password = it }, isError = false, texto = "Contraseña")

        // Botón de Login
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (viewModel.login(email, password)) {
                    navController?.navigate("account_screen") // Ir a la pantalla de cuentas
                } else {
                    Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }

        // Botón para registrarse
        Spacer(modifier = Modifier.height(16.dp))
        Divider() // Línea separadora
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("¿No tienes cuenta?")
            TextButton(onClick = { navController?.navigate("register_screen") }) {
                Text("Crear cuenta")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = null, viewModel = AccountsListsViewModel())
}
