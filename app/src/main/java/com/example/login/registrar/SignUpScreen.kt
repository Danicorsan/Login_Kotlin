package com.example.login.registrar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.login.R
import com.example.login.base.CampoForm
import com.example.login.base.MediumSpace
import com.example.login.base.SmallSpace
import com.example.login.utils.validate

@Composable
fun SignUpScreen(modifier: Modifier = Modifier){

    var nombre by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") } //mutable es el observador y remember es para guardar el estado
    var password =
        rememberSaveable { mutableStateOf("") } //By remember es mas facil no hace falta llamar al objeto.value

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_launcher_foreground), "Icono Android"
            )
            Text("Bienvenido al Registro")
            MediumSpace()
            CampoForm(nombre,false,"Nombre",{nombre = it})
            SmallSpace()
            CampoForm(apellidos,false,"Apellidos",{apellidos = it})
            SmallSpace()
            CampoForm(email,validate(email),"Correo",{email = it})
            SmallSpace()
            CampoForm(password.value,false,"Contraseña",{password.value = it})
            SmallSpace()
            Button(onClick = {

            }) {
                Text("Registrar")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUp(){
    SignUpScreen()
}