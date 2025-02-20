package com.example.login.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    var state by mutableStateOf(MainState(activeAccount = false))
        private set

    //Comprobar en el DataStore si el usuario ha iniciado sesion
    init {
        //comprobarLogin()
    }
    /*
    private fun comprobarLogin() {
        viewModelScope.launch {
            //Se lee el valor del fichero DATASTORE
            session.isUserLoggedIn().collect { isLoggedIn ->
                //Se ejecuta el codigo que se encuentra en la funcion HomeScreewn
                state = state.copy(activeAccount = isLoggedIn)
            }
        }
    }

     */

}