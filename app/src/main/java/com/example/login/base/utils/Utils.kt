package com.example.login.base.utils

fun validate(email: String): Boolean {

 val emailPattern = "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}" //cadena
 val regex = Regex(emailPattern) //Expresion Regular
 return regex.matches(email)
}