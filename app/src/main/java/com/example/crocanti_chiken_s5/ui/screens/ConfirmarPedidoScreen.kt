package com.example.crocanti_chiken_s5.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crocanti_chiken_s5.viewmodel.CarritoViewModel

@Composable
fun ConfirmarPedidoScreen(
    viewModel: CarritoViewModel,
    onPedidoConfirmado: () -> Unit,
    onVolver: () -> Unit
) {
    val uiState = viewModel.uiState
    var tiendaSeleccionada by remember { mutableStateOf("") }
    val tiendas = viewModel.getTiendas()

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("📍", fontSize = 60.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Seleccionar Tienda", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        tiendas.forEach { tienda ->
            val isSelected = tiendaSeleccionada == tienda
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface
                ),
                onClick = { tiendaSeleccionada = tienda }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = isSelected, onClick = { tiendaSeleccionada = tienda })
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(tienda, fontSize = 16.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                viewModel.seleccionarTienda(tiendaSeleccionada)
                onPedidoConfirmado()
            },
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(28.dp),
            enabled = tiendaSeleccionada.isNotEmpty()
        ) {
            Text("Confirmar Pedido", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = onVolver) {
            Text("Volver al carrito")
        }
    }
}