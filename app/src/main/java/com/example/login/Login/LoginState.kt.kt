package com.moronlu18.loginjetpackcompose.login

data class LoginState(
    //RNLOGIN_1 : Formato Passqword incorrecto
    // 8 caracteres mínimo (ya se contempla el nulo)
    // A-> 1 carácter mayúscula
    // 2-> mínimio 1 carácter número
    // ? -> 1 caracter especial
    val passwordErrorFormat: String? = null,

    //RNLOGIN_2 : Formato email incorrecto
    // pattern y matcher
    val emailErrorFormat: String? = null,

    //RNLOGIN_3 : Usuario no registrado
    val userError: String? = null,


    //RNLOGIN_4 : Success
    val success: Boolean = false,

    //Requerimientos NO Funcional
    //RNFLOGIN_1 : Tiempo de espera de conexión
    val isLoading: Boolean = false,

    //RNFLOGIN_2 : Error de conexión
    val isOffline: Boolean = false,

    )