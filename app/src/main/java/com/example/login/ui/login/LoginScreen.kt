package com.example.login.ui.login

import android.app.AlertDialog
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.login.R
import com.example.login.base.CampoFormulario

@Composable
fun LoginScreen(
    email:String,
    password:String,
    onclickCrearCuenta: () -> Unit,
    onSucces: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>()
) {
    val state = viewModel.state
    val context = LocalContext.current

    // Usamos LaunchedEffect para actualizar el estado en el ViewModel
    // tan pronto como recibimos los parámetros `email` y `password`.
    LaunchedEffect(email, password) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.setCredentialsFromSignUp(email, password)
        }
    }

    if (state.success) {
            onSucces()
    } else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Android Icon",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Bienvenido", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(24.dp))
            CampoFormulario(
                value = state.email,
                onValueChange = { viewModel.onEmailChange(it) },
                isError = state.isEmailError,
                texto = "Correo"
            )
            if (state.emailErrorFormat != null) {
                Text(state.emailErrorFormat, color = MaterialTheme.colorScheme.error, style = TextStyle(
                    fontSize = 12.sp, textAlign = TextAlign.Center,
                ))
            }
            Spacer(modifier = Modifier.height(8.dp))
            CampoFormulario(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                isError = state.isPasswordError,
                texto = "Contraseña",
                isPassword = true
            )
            if (state.passwordErrorFormat != null) {
                Text(state.passwordErrorFormat, color = MaterialTheme.colorScheme.error, style = TextStyle(
                    fontSize = 12.sp, textAlign = TextAlign.Center,
                ))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    viewModel.login(
                        onSuccess = { /* No hacemos nada aquí, manejado por LaunchedEffect */ },
                        onError = { error ->
                            showErrorDialog(context, error)
                        }
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isLoading
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Iniciar sesión")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("¿No tienes cuenta?")
                TextButton(onClick = { onclickCrearCuenta() }) {
                    Text("Crear cuenta")
                }
            }
        }
    }
}

fun showErrorDialog(context: Context, error: String) {
    AlertDialog.Builder(context)
        .setTitle("Error")
        .setMessage(error)
        .setPositiveButton("Aceptar", null)
        .show()
}

/*
@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        onclickCrearCuenta = { /* No hacer nada en este ejemplo */ },
        onSucces = {},
        viewModel = LoginViewModel()
    )
}
*/