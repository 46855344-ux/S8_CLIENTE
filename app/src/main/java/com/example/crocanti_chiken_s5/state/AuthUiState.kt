package com.example.crocanti_chiken_s5.state

import com.example.crocanti_chiken_s5.data.local.Usuario

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class Success(val usuario: Usuario) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}