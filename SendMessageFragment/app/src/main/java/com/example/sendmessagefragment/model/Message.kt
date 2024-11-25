package com.example.sendmessagefragment.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(val personaE: Persona, val personaReceptor: Persona,val mensaje:String):Parcelable {
    companion object {
        //clave que se utiliza al parcelar un objeto de la clase Message
        const val KEY: String = "message"
    }
}