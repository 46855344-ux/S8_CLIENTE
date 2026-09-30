package com.example.crocanti_chiken_s5.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.crocanti_chiken_s5.data.local.Producto
import com.example.crocanti_chiken_s5.ui.components.ItemCard
import com.example.crocanti_chiken_s5.viewmodel.MenuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: MenuViewModel,
    onProductoClick: (Int) -> Unit,
    onCarritoClick: () -> Unit,
    onAddToCart: (Producto) -> Unit
) {
    val uiState = viewModel.uiState
    val categorias = listOf("Todos", "Pollo", "Combos", "Bebidas", "Postres", "Adicionales")

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        TopAppBar(
            title = { Text("Crocanti Chicken", color = MaterialTheme.colorScheme.onPrimary) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            actions = {
                IconButton(onClick = onCarritoClick) {
                    Box {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito", tint = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        )

        OutlinedTextField(
            value = uiState.busqueda,
            onValueChange = { viewModel.buscar(it) },
            placeholder = { Text("Buscar...") },
            leadingIcon = { Icon(Icons.Default.Search, null) },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(24.dp)
        )

        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items = categorias) { cat ->
                FilterChip(
                    selected = uiState.categoriaSeleccionada == cat,
                    onClick = { viewModel.filtrarPorCategoria(cat) },
                    label = { Text(cat) },
                    colors = if (uiState.categoriaSeleccionada == cat)
                        FilterChipDefaults.filterChipColors(containerColor = MaterialTheme.colorScheme.primary, labelColor = MaterialTheme.colorScheme.onPrimary)
                    else FilterChipDefaults.filterChipColors()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = uiState.productos) { producto ->
                ItemCard(
                    producto = producto,
                    onAddClick = { onAddToCart(producto) },
                    onDetalleClick = { onProductoClick(producto.id) },
                    modifier = Modifier.height(220.dp)
                )
            }
        }
    }
}