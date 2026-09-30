package com.example.crocanti_chiken_s5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.crocanti_chiken_s5.data.local.AppDatabase
import com.example.crocanti_chiken_s5.data.local.SeedData
import com.example.crocanti_chiken_s5.data.repository.AuthRepository
import com.example.crocanti_chiken_s5.data.repository.CarritoRepository
import com.example.crocanti_chiken_s5.data.repository.PedidoRepository
import com.example.crocanti_chiken_s5.data.repository.ProductoRepository
import com.example.crocanti_chiken_s5.ui.navigation.AppNavigation
import com.example.crocanti_chiken_s5.ui.theme.CrocantiTheme
import com.example.crocanti_chiken_s5.viewmodel.AuthViewModel
import com.example.crocanti_chiken_s5.viewmodel.CarritoViewModel
import com.example.crocanti_chiken_s5.viewmodel.MenuViewModel

class MainActivity : ComponentActivity() {

    private lateinit var database: AppDatabase

    // Crear los ViewModels FUERA del composable
    private val authViewModel by lazy {
        AuthViewModel(AuthRepository(database.usuarioDao()))
    }

    private val menuViewModel by lazy {
        MenuViewModel(ProductoRepository(database.productoDao()))
    }

    private val carritoViewModel by lazy {
        CarritoViewModel(
            CarritoRepository(database.carritoItemDao()),
            PedidoRepository(database.pedidoDao())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Crear la base de datos
        database = AppDatabase.getDB(this)

        // Insertar productos de prueba si la base de datos está vacía
        SeedData.insertarProductos(database)

        setContent {
            CrocantiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    AppNavigation(
                        navController = navController,
                        authViewModel = authViewModel,
                        menuViewModel = menuViewModel,
                        carritoViewModel = carritoViewModel
                    )
                }
            }
        }
    }
}