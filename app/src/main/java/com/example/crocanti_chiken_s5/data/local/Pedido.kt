package com.example.crocanti_chiken_s5.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos")
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val numeroOrden: String,
    val total: Double,
    val tienda: String,
    val estado: String = "PENDIENTE",
    val fecha: String = ""
)