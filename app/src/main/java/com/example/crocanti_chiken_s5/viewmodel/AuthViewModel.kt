package com.example.crocanti_chiken_s5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocanti_chiken_s5.data.local.Usuario
import com.example.crocanti_chiken_s5.data.repository.AuthRepository
import com.example.crocanti_chiken_s5.state.AuthUiState
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    var uiState: AuthUiState = AuthUiState.Idle
        private set

    fun registrar(nombre: String, correo: String, clave: String, rol: String) {
        if (clave.length < 6) {
            uiState = AuthUiState.Error("La contraseña debe tener mínimo 6 caracteres")
            return
        }

        if (correo.isBlank()) {
            uiState = AuthUiState.Error("El correo no puede estar vacío")
            return
        }

        if (nombre.isBlank()) {
            uiState = AuthUiState.Error("El nombre no puede estar vacío")
            return
        }

        viewModelScope.launch {
            uiState = AuthUiState.Loading
            repository.registrar(Usuario(nombre = nombre, correo = correo, clave = clave, rol = rol))
            uiState = AuthUiState.Success(Usuario(0, nombre, correo, clave, rol))
        }
    }

    fun login(correo: String, clave: String) {
        if (clave.length < 6) {
            uiState = AuthUiState.Error("La contraseña debe tener mínimo 6 caracteres")
            return
        }

        if (correo.isBlank()) {
            uiState = AuthUiState.Error("El correo no puede estar vacío")
            return
        }

        viewModelScope.launch {
            uiState = AuthUiState.Loading
            val usuario = repository.login(correo, clave)
            uiState = if (usuario != null) {
                AuthUiState.Success(usuario)
            } else {
                AuthUiState.Error("Credenciales incorrectas")
            }
        }
    }

    fun resetState() {
        uiState = AuthUiState.Idle
    }
}