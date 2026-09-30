package com.example.crocanti_chiken_s5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crocanti_chiken_s5.data.local.CarritoItem
import com.example.crocanti_chiken_s5.data.local.Pedido
import com.example.crocanti_chiken_s5.data.local.Producto
import com.example.crocanti_chiken_s5.data.repository.CarritoRepository
import com.example.crocanti_chiken_s5.data.repository.PedidoRepository
import com.example.crocanti_chiken_s5.state.CarritoUiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.collections.find

class CarritoViewModel(
    private val carritoRepo: CarritoRepository,
    private val pedidoRepo: PedidoRepository
) : ViewModel() {

    var uiState: CarritoUiState = CarritoUiState()
        private set

    init {
        observarCarrito()
    }

    private fun observarCarrito() {
        viewModelScope.launch {
            carritoRepo.obtenerItems().collectLatest { items ->
                val subtotal = items.sumOf { it.cantidad.toDouble() }
                uiState = uiState.copy(items = items, subtotal = subtotal, total = subtotal)
            }
        }
    }

    fun agregarProducto(producto: Producto) {
        viewModelScope.launch {
            val existe = uiState.items.find { it.productoId == producto.id }
            if (existe != null) {
                carritoRepo.aumentarCantidad(producto.id)
            } else {
                carritoRepo.agregarItem(CarritoItem(productoId = producto.id, cantidad = 1))
            }
        }
    }

    fun aumentarCantidad(productoId: Int) {
        viewModelScope.launch { carritoRepo.aumentarCantidad(productoId) }
    }

    fun disminuirCantidad(productoId: Int) {
        viewModelScope.launch { carritoRepo.disminuirCantidad(productoId) }
    }

    fun eliminarProducto(productoId: Int) {
        viewModelScope.launch { carritoRepo.eliminarItem(productoId) }
    }

    fun seleccionarTienda(tienda: String) {
        uiState = uiState.copy(tiendaSeleccionada = tienda)
    }

    fun confirmarPedido(): String? {
        if (uiState.items.isEmpty() || uiState.tiendaSeleccionada.isEmpty()) return null

        val numeroOrden = "ORD-${System.currentTimeMillis().toString().takeLast(6)}"
        val fecha = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())

        val pedido = Pedido(
            numeroOrden = numeroOrden,
            total = uiState.total,
            tienda = uiState.tiendaSeleccionada,
            fecha = fecha
        )

        viewModelScope.launch {
            pedidoRepo.registrarPedido(pedido)
            carritoRepo.vaciarCarrito()
            uiState = uiState.copy(pedidoConfirmado = pedido)
        }

        return numeroOrden
    }

    fun vaciarCarrito() {
        viewModelScope.launch {
            carritoRepo.vaciarCarrito()
            uiState = CarritoUiState()
        }
    }

    fun getTiendas() = listOf(
        "Tienda Chiclayo Centro",
        "Tienda Chiclayo Norte",
        "Tienda Chiclayo Sur"
    )
}