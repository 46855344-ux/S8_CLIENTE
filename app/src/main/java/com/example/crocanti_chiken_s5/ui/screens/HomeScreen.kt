package com.example.crocanti_chiken_s5.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crocanti_chiken_s5.data.local.Usuario

@Composable
fun HomeScreen(usuario: Usuario, onIrAMenu: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("", fontSize = 80.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Bienvenido a Crocanti Chicken", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Hola, ${usuario.nombre}", color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onIrAMenu,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.width(200.dp)
        ) {
            Text("Ver Menú", fontSize = 16.sp)
        }
    }
}