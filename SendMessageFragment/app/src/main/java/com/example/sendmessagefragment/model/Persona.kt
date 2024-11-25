package com.example.sendmessagefragment.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Persona(val nombre: String, val apellido: String, val dni: String) : Parcelable {
    companion object {
        //clave que se utiliza al parcelar un objeto de la clase Message
        const val KEY: String = "persona"
    }
}