package com.example.crocanti_chiken_s5.data.repository

import com.example.crocanti_chiken_s5.data.local.Pedido
import com.example.crocanti_chiken_s5.data.local.PedidoDao
import kotlinx.coroutines.flow.Flow

class PedidoRepository(private val dao: PedidoDao) {

    suspend fun registrarPedido(pedido: Pedido) = dao.registrarPedido(pedido)

    fun obtenerPedidos(): Flow<List<Pedido>> = dao.obtenerPedidos()

    suspend fun obtenerPedidoPorId(id: Int): Pedido? = dao.obtenerPedidoPorId(id)
}