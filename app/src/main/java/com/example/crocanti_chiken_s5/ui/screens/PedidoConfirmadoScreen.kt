package com.example.crocanti_chiken_s5.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crocanti_chiken_s5.data.local.Pedido

@Composable
fun PedidoConfirmadoScreen(
    pedido: Pedido,
    onVolverAlInicio: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("✅", fontSize = 80.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("¡Pedido Confirmado!", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Número de Orden:", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(pedido.numeroOrden, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(12.dp))
                Text("Tienda: ${pedido.tienda}", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Total: S/ ${"%.2f".format(pedido.total)}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Fecha: ${pedido.fecha}", fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onVolverAlInicio,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text("Volver al Inicio", fontSize = 16.sp)
        }
    }
}