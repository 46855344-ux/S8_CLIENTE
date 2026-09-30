package com.example.crocanti_chiken_s5.data.repository

import com.example.crocanti_chiken_s5.data.local.Producto
import com.example.crocanti_chiken_s5.data.local.ProductoDao
import kotlinx.coroutines.flow.Flow

class ProductoRepository(private val productoDao: ProductoDao) {

    fun getProductos(): Flow<List<Producto>> = productoDao.obtenerProductos()

    fun getProductosPorCategoria(categoria: String): Flow<List<Producto>> =
        productoDao.obtenerProductosPorCategoria(categoria)

    fun getProductosEnPromo(): Flow<List<Producto>> =
        productoDao.obtenerProductosEnPromo()

    fun buscarProducto(query: String): Flow<List<Producto>> =
        productoDao.buscarProducto(query)

    suspend fun getProductoPorId(id: Int): Producto? =
        productoDao.obtenerProductoPorId(id)

    suspend fun insertarProducto(producto: Producto) {
        productoDao.insertarProducto(producto)
    }
}