package com.example.login.ui.registrar

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
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.login.R
import com.example.login.base.CampoFormulario
import com.example.login.base.utils.ShowErrorDialog

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel(),  // Obtén la instancia del ViewModel usando viewModel()
    onRegister: (String, String) -> Unit // Recibimos email y password desde aquí
) {

    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

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
        Text("Crea tu cuenta", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(24.dp))
        CampoFormulario(
            value = viewModel.state.userName,
            onValueChange = { viewModel.onNameChange(it) },
            isError = viewModel.state.nameUserErrorFormat != null,
            texto = "Nombre",
            errorMessage = viewModel.state.nameUserErrorFormat.orEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(
            value = viewModel.state.userSurname,
            onValueChange = { viewModel.onSurnameChange(it) },
            isError = viewModel.state.userErrorFormat != null,
            texto = "Apellidos",
            errorMessage = viewModel.state.userErrorFormat.orEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(
            value = viewModel.state.email,
            onValueChange = { viewModel.onEmailChange(it) },
            isError = viewModel.state.emailErrorFormat != null,
            texto = "Correo",
            errorMessage = viewModel.state.emailErrorFormat.orEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))
        CampoFormulario(
            value = viewModel.state.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            isError = viewModel.state.passwordErrorFormat != null,
            texto = "Contraseña",
            errorMessage = viewModel.state.passwordErrorFormat.orEmpty()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.register(
                    onSuccess = { onRegister(viewModel.state.email, viewModel.state.password) },
                    onError = { error ->
                        errorMessage = error
                        showErrorDialog = true
                    }
                )
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !viewModel.state.isLoading
        ) {
            Text(if (viewModel.state.isLoading) "Registrando..." else "Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        Text("¿Ya tienes cuenta?")
        TextButton(onClick = { navController.navigate("login_screen") }) {
            Text("Inicia sesión")
        }
    }

    if (showErrorDialog) {
        ShowErrorDialog(errorMessage) {
            showErrorDialog = false // Ocultar el cuadro de diálogo cuando se haga clic en "OK"
        }
    }
}



