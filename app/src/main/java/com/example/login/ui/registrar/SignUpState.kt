package com.example.jetpackcomposelogincorregido.ui.register


data class AccountRegisterState(
    //RNREGISTER_1 : Formato Passqword incorrecto
    // 8 caracteres mínimo (ya se contempla el nulo)
    // A-> 1 carácter mayúscula
    // 2-> mínimio 1 carácter número
    // ? -> 1 caracter especial
    val passwordErrorFormat: String? = null,

    //RNREGISTER_2 : Formato email incorrecto
    // pattern y matcher
    val emailErrorFormat: String? = null,


    //RNREGISTER_3 : Formato name user incorrecto
    // pattern y matcher
    val nameUserErrorFormat: String? = null,

    //RNREGISTER_4: Nombre y apellidos del usuario no nulo
    val userErrorFormat: String? = null,

    //RNREGISTER_5: Fecha posterior a la actual
    val dateError: String? = null,

    val emailError: Int? = null,
    val passwordError: Int? = null,

    //RNREGISTER_6: Cuenta ya existe (name user, email)
    val accountExitsError: Boolean = false,

    //RNREGISTER_7:Error que venga de la infraestructura
    // (bases de dats, netflix...)
    val serverError: String? = null,

    //RNREGISTER_8 : Success
    val success: Boolean = false,



    //Requerimientos NO Funcional
    //RNFLOGIN_1 : Tiempo de espera de conexión
    val isLoading: Boolean = false,

    )