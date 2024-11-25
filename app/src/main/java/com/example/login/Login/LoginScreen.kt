package com.example.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.login.base.CampoForm
import com.example.login.utils.validate
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(navController: NavHostController, modifier: Modifier = Modifier) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isErrorEmail by rememberSaveable { mutableStateOf(false) }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    var isOffline by rememberSaveable { mutableStateOf(false) }

    //para meter un delay
    LaunchedEffect(key1 = isLoading) {
        if (isLoading) {
            delay(3000)
            isOffline = true
            isLoading = false
        }
    }

    if (isOffline) {
        OfflineUI()
    } else if (!isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
            ) {
                Text("Login Jetpack Compose", fontSize = 30.sp)
                CampoForm(email, isErrorEmail, "Email", {
                    email = it
                    isErrorEmail = !validate(email)
                })
                CampoForm(password, false, "Contraseña", {
                    password = it
                })
                Button(onClick = { isLoading = true }) {
                    Text("Login")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(R.string.no_tienes_cuenta))
                    TextButton(onClick = {
                        // Aquí es donde se realiza la navegación a la pantalla de registro
                        navController.navigate("sign_up_screen") // Usa navController para navegar
                    }) {
                        Text("CREAR".uppercase())
                    }
                }
            }
        }
    } else {
        LoadingUI()
    }
}

@Composable
fun LoadingUI(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = androidx.compose.ui.graphics.Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(60.dp)
                    .padding(10.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
            Spacer(modifier = modifier.size(15.dp))
            Text("Espere un momento...", fontSize = 20.sp)
        }
    }
}

@Composable
fun OfflineUI(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = androidx.compose.ui.graphics.Color.Cyan),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Icono Android"
            )
            Text("No tienes conexión a la red")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}
