import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.utils.validate

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

    var email by rememberSaveable { mutableStateOf("") } //mutable es el observador y remember es para guardar el estado
    var password =
        rememberSaveable { mutableStateOf("") } //By remember es mas facil no hace falta llamar al objeto.value
    var isErrorEmail by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {
            Text("Login Jetpack Compose", fontSize = 30.sp)
            TextField(
                singleLine = true,
                value = email,
                isError = isErrorEmail,
                onValueChange = { email = it
                                isErrorEmail=validate(email)
                },
                label = { Text("Email") },
                supportingText = {
                    Row {
                        Text(if (isErrorEmail) "Formato Incorrecto: " else "")
                        Spacer(Modifier.weight(1f))
                        Text("${email.length}/30")
                    }
                }
            )
            TextField(
                singleLine = true,
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña") }
            )
            Button(onClick = {}) {
                Text("Login")
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("¿No tienes cuenta?")
                TextButton(onClick = {}) {
                    Text("CREAR".uppercase())
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}