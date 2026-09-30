package com.example.crocanti_chiken_s5.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PedidoDao {
    @Insert
    suspend fun registrarPedido(pedido: Pedido)

    @Query("SELECT * FROM pedidos ORDER BY id DESC")
    fun obtenerPedidos(): Flow<List<Pedido>>

    @Query("SELECT * FROM pedidos WHERE id = :id LIMIT 1")
    suspend fun obtenerPedidoPorId(id: Int): Pedido?
}