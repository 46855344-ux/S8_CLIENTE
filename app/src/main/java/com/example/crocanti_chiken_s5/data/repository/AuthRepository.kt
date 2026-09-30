package com.example.crocanti_chiken_s5.data.repository

import com.example.crocanti_chiken_s5.data.local.Usuario
import com.example.crocanti_chiken_s5.data.local.UsuarioDao

class AuthRepository(private val usuarioDao: UsuarioDao) {

    suspend fun registrar(usuario: Usuario) {
        usuarioDao.registrar(usuario)
    }

    suspend fun login(correo: String, clave: String): Usuario? {
        return usuarioDao.login(correo, clave)
    }
}