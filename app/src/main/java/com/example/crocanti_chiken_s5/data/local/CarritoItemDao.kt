package com.example.crocanti_chiken_s5.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CarritoItemDao {
    @Insert
    suspend fun agregarItem(item: CarritoItem)

    @Query("SELECT * FROM carrito_items")
    fun obtenerItems(): Flow<List<CarritoItem>>

    @Query("UPDATE carrito_items SET cantidad = cantidad + 1 WHERE productoId = :productoId")
    suspend fun aumentarCantidad(productoId: Int)

    @Query("UPDATE carrito_items SET cantidad = cantidad - 1 WHERE productoId = :productoId AND cantidad > 1")
    suspend fun disminuirCantidad(productoId: Int)

    @Query("DELETE FROM carrito_items WHERE productoId = :productoId")
    suspend fun eliminarItem(productoId: Int)

    @Query("DELETE FROM carrito_items")
    suspend fun vaciarCarrito()
}