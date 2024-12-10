package com.example.login.data.model


/**
 * Account exception, contiene todos los posibles errores que se puedan
 * encontrar en el caso de uso de Login y de SignUp
 *
 * @constructor
 *
 * @param message
 */


sealed class AccountException(message:String):Exception(message) {
    data class TakenEmail(var email:String): AccountException("Ya existe una cuenta con $email")
    data object NoExistAccount : AccountException("La cuenta no existe")
    //Devuelve en toString el nombre del objeto

}