package com.example.crocanti_chiken_s5.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Insert
    suspend fun registrar(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE correo=:correo AND clave=:clave LIMIT 1")
    suspend fun login(correo: String, clave: String): Usuario?
}