package com.example.crocanti_chiken_s5.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {
    @Insert
    suspend fun insertarProducto(producto: Producto)

    @Query("SELECT * FROM productos WHERE disponible = 1")
    fun obtenerProductos(): Flow<List<Producto>>

    @Query("SELECT * FROM productos WHERE categoria = :categoria AND disponible = 1")
    fun obtenerProductosPorCategoria(categoria: String): Flow<List<Producto>>

    @Query("SELECT * FROM productos WHERE enPromocion = 1 AND disponible = 1")
    fun obtenerProductosEnPromo(): Flow<List<Producto>>

    @Query("SELECT * FROM productos WHERE nombre LIKE '%' || :query || '%' AND disponible = 1")
    fun buscarProducto(query: String): Flow<List<Producto>>

    @Query("SELECT * FROM productos WHERE id = :id LIMIT 1")
    suspend fun obtenerProductoPorId(id: Int): Producto?
}