package com.example.crocanti_chiken_s5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocanti_chiken_s5.data.local.Producto
import com.example.crocanti_chiken_s5.data.repository.ProductoRepository
import com.example.crocanti_chiken_s5.state.MenuUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.collections.find

class MenuViewModel(private val repository: ProductoRepository) : ViewModel() {

    var uiState: MenuUiState = MenuUiState()
        private set

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        viewModelScope.launch {
            repository.getProductos().collectLatest { productos ->
                uiState = uiState.copy(productos = productos, isLoading = false)
            }
        }
    }

    fun filtrarPorCategoria(categoria: String) {
        uiState = uiState.copy(categoriaSeleccionada = categoria)
        viewModelScope.launch {
            repository.getProductosPorCategoria(categoria).collectLatest { productos ->
                uiState = uiState.copy(productos = productos)
            }
        }
    }

    fun buscar(query: String) {
        uiState = uiState.copy(busqueda = query)
        viewModelScope.launch {
            repository.buscarProducto(query).collectLatest { productos ->
                uiState = uiState.copy(productos = productos)
            }
        }
    }

    fun getProductoPorId(id: Int): Producto? =
        uiState.productos.find { it.id == id }
}