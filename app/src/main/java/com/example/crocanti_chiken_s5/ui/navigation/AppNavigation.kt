package com.example.crocanti_chiken_s5.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crocanti_chiken_s5.data.local.Usuario
import com.example.crocanti_chiken_s5.ui.screens.CarritoScreen
import com.example.crocanti_chiken_s5.ui.screens.ConfirmarPedidoScreen
import com.example.crocanti_chiken_s5.ui.screens.DetailScreen
import com.example.crocanti_chiken_s5.ui.screens.HomeScreen
import com.example.crocanti_chiken_s5.ui.screens.ListScreen
import com.example.crocanti_chiken_s5.ui.screens.LoginScreen
import com.example.crocanti_chiken_s5.ui.screens.PedidoConfirmadoScreen
import com.example.crocanti_chiken_s5.ui.screens.RegistroScreen
import com.example.crocanti_chiken_s5.ui.screens.SplashScreen
import com.example.crocanti_chiken_s5.viewmodel.AuthViewModel
import com.example.crocanti_chiken_s5.viewmodel.CarritoViewModel
import com.example.crocanti_chiken_s5.viewmodel.MenuViewModel

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val REGISTRO = "registro"
    const val HOME = "home"
    const val LIST = "list"
    const val DETAIL = "detail/{productoId}"
    const val CARRITO = "carrito"
    const val CONFIRMAR_PEDIDO = "confirmar_pedido"
    const val PEDIDO_CONFIRMADO = "pedido_confirmado"
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    menuViewModel: MenuViewModel,
    carritoViewModel: CarritoViewModel
) {
    var usuarioActual by remember { mutableStateOf<Usuario?>(null) }

    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            SplashScreen {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.SPLASH) { inclusive = true }
                }
            }
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = { usuario ->
                    usuarioActual = usuario
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onIrRegistro = {
                    navController.navigate(Routes.REGISTRO)
                }
            )
        }

        composable(Routes.REGISTRO) {
            RegistroScreen(
                viewModel = authViewModel,
                onRegistroExitoso = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTRO) { inclusive = true }
                    }
                },
                onVolverLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.HOME) {
            usuarioActual?.let { usuario ->
                HomeScreen(
                    usuario = usuario,
                    onIrAMenu = { navController.navigate(Routes.LIST) }
                )
            }
        }

        composable(Routes.LIST) {
            ListScreen(
                viewModel = menuViewModel,
                onProductoClick = { id -> navController.navigate("detail/$id") },
                onCarritoClick = { navController.navigate(Routes.CARRITO) },
                onAddToCart = { producto -> carritoViewModel.agregarProducto(producto) }
            )
        }

        composable(Routes.DETAIL) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("productoId")?.toIntOrNull() ?: 0
            val producto = menuViewModel.getProductoPorId(id)
            if (producto != null) {
                DetailScreen(
                    producto = producto,
                    onBackClick = { navController.popBackStack() },
                    onAgregarCarrito = {
                        carritoViewModel.agregarProducto(producto)
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(Routes.CARRITO) {
            CarritoScreen(
                viewModel = carritoViewModel,
                onBackClick = { navController.popBackStack() },
                onConfirmarPedido = { navController.navigate(Routes.CONFIRMAR_PEDIDO) }
            )
        }

        composable(Routes.CONFIRMAR_PEDIDO) {
            ConfirmarPedidoScreen(
                viewModel = carritoViewModel,
                onPedidoConfirmado = {
                    navController.navigate(Routes.PEDIDO_CONFIRMADO) {
                        popUpTo(Routes.CARRITO) { inclusive = true }
                    }
                },
                onVolver = { navController.popBackStack() }
            )
        }

        composable(Routes.PEDIDO_CONFIRMADO) {
            val pedido = carritoViewModel.uiState.pedidoConfirmado
            if (pedido != null) {
                PedidoConfirmadoScreen(
                    pedido = pedido,
                    onVolverAlInicio = {
                        carritoViewModel.vaciarCarrito()
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.HOME) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}