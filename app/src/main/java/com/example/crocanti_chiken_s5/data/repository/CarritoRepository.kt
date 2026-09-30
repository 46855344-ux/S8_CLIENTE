package com.example.crocanti_chiken_s5.data.repository

import com.example.crocanti_chiken_s5.data.local.CarritoItem
import com.example.crocanti_chiken_s5.data.local.CarritoItemDao
import kotlinx.coroutines.flow.Flow

class CarritoRepository(private val dao: CarritoItemDao) {

    fun obtenerItems(): Flow<List<CarritoItem>> = dao.obtenerItems()

    suspend fun agregarItem(item: CarritoItem) = dao.agregarItem(item)

    suspend fun aumentarCantidad(productoId: Int) = dao.aumentarCantidad(productoId)

    suspend fun disminuirCantidad(productoId: Int) = dao.disminuirCantidad(productoId)

    suspend fun eliminarItem(productoId: Int) = dao.eliminarItem(productoId)

    suspend fun vaciarCarrito() = dao.vaciarCarrito()
}