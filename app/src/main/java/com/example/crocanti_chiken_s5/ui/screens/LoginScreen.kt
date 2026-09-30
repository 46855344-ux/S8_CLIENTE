package com.example.crocanti_chiken_s5.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crocanti_chiken_s5.data.local.Usuario
import com.example.crocanti_chiken_s5.state.AuthUiState
import com.example.crocanti_chiken_s5.ui.components.AppLogo
import com.example.crocanti_chiken_s5.ui.components.InputField
import com.example.crocanti_chiken_s5.viewmodel.AuthViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    onLoginSuccess: (Usuario) -> Unit,
    onIrRegistro: () -> Unit
) {
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }

    val uiState = viewModel.uiState
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // Cuando el login es exitoso, navegar al Home
    when (val state = uiState) {
        is AuthUiState.Success -> {
            LaunchedEffect(state.usuario) {
                onLoginSuccess(state.usuario)
            }
        }
        else -> {}
    }

    // Función para hacer login
    val hacerLogin = {
        keyboardController?.hide()
        focusManager.clearFocus()
        viewModel.login(correo, clave)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(32.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        AppLogo(size = 100)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Crocanti Chicken",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text("Iniciar Sesión", fontSize = 26.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(32.dp))

        // Campo Correo
        InputField(
            value = correo,
            onValueChange = { correo = it },
            label = "Correo electrónico",
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.None
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Contraseña
        InputField(
            value = clave,
            onValueChange = { clave = it },
            label = "Contraseña",
            modifier = Modifier.fillMaxWidth(),
            isPassword = true,
            imeAction = ImeAction.None
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón Ingresar
        Button(
            onClick = hacerLogin,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .pointerInput(Unit) {
                    detectTapGestures { hacerLogin() }
                },
            shape = RoundedCornerShape(28.dp),
            enabled = uiState !is AuthUiState.Loading &&
                    correo.isNotBlank() &&
                    clave.length >= 6
        ) {
            if (uiState is AuthUiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Ingresar", fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para ir a Registro
        TextButton(onClick = onIrRegistro) {
            Text("¿No tienes cuenta? Regístrate aquí", color = MaterialTheme.colorScheme.primary)
        }

        // Mostrar error si existe
        if (uiState is AuthUiState.Error) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                uiState.message,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
    }
}