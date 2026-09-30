package com.example.crocanti_chiken_s5.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val categoria: String,
    val precio: Double,
    val descripcion: String = "",
    val imagenEmoji: String = "🍗",
    val disponible: Boolean = true,
    val enPromocion: Boolean = false
)