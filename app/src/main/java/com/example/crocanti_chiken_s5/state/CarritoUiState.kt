package com.example.crocanti_chiken_s5.state

import com.example.crocanti_chiken_s5.data.local.CarritoItem
import com.example.crocanti_chiken_s5.data.local.Pedido


data class CarritoUiState(
    val items: List<CarritoItem> = emptyList(),
    val subtotal: Double = 0.0,
    val total: Double = 0.0,
    val tiendaSeleccionada: String = "",
    val pedidoConfirmado: Pedido? = null
)