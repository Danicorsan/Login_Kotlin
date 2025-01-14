package com.example.login.data.network

sealed class BaseResultList <out T>{
    data class Success<T> (var data: ArrayList<T>?): BaseResultList<T>()
    data class Error<T>(var exception:Exception): BaseResultList<Nothing>()
}