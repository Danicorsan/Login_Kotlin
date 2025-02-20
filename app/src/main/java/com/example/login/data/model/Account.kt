package com.example.login.data.model

import androidx.compose.ui.text.intl.Locale
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Account(
    @PrimaryKey
    val id:Int,
    val email: String,
    val password: String,
    @ColumnInfo(name= "username")
    val username: String?,
    val name: String,
    val surname: String,
    val birthdate: Date?,
    @ColumnInfo(name= "firebase_uid")
    val firebaseUID: String? = null,
    val photoURL:String? = null,
    val createdAt:String? = null,
    val updateAt:String? = null
    ) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        return email == other.email
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }

}
