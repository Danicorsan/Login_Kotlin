package com.example.login.data.model

data class Account(
    val email: String,
    val password: String,
    val username: String,
    val name: String,
    val surname: String,
    val birthdate: String

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
