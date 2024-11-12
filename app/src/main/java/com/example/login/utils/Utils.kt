package com.example.login.utils

fun validate(email: String): Boolean {
 //return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();

 val emailPattern = "[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}" //cadena
 val regex = Regex(emailPattern) //Expresion Regular
 return regex.matches(email)
}