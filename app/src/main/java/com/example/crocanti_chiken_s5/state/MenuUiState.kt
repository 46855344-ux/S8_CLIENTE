package com.example.crocanti_chiken_s5.state

import com.example.crocanti_chiken_s5.data.local.Producto

data class MenuUiState(
    val productos: List<Producto> = emptyList(),
    val categoriaSeleccionada: String = "Todos",
    val busqueda: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)