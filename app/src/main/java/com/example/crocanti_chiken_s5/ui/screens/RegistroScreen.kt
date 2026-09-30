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
import com.example.crocanti_chiken_s5.state.AuthUiState
import com.example.crocanti_chiken_s5.ui.components.AppLogo
import com.example.crocanti_chiken_s5.ui.components.InputField
import com.example.crocanti_chiken_s5.viewmodel.AuthViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistroScreen(
    viewModel: AuthViewModel,
    onRegistroExitoso: () -> Unit,
    onVolverLogin: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }

    val uiState = viewModel.uiState
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    when (uiState) {
        is AuthUiState.Success -> {
            LaunchedEffect(Unit) { onRegistroExitoso() }
        }
        else -> {}
    }

    val registrar = {
        keyboardController?.hide()
        focusManager.clearFocus()
        viewModel.registrar(nombre, correo, clave, "cliente")
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
        AppLogo(size = 100)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Crocanti Chicken",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text("Crear Cuenta", fontSize = 26.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(32.dp))

        InputField(
            value = nombre,
            onValueChange = { nombre = it },
            label = "Nombre completo",
            modifier = Modifier.fillMaxWidth(),
            imeAction = ImeAction.None
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputField(
            value = correo,
            onValueChange = { correo = it },
            label = "Correo electrónico",
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.None
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputField(
            value = clave,
            onValueChange = { clave = it },
            label = "Contraseña (mín. 6 caracteres)",
            modifier = Modifier.fillMaxWidth(),
            isPassword = true,
            imeAction = ImeAction.None
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = registrar,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .pointerInput(Unit) {
                    detectTapGestures { registrar() }
                },
            shape = RoundedCornerShape(28.dp),
            enabled = nombre.isNotBlank() &&
                    correo.isNotBlank() &&
                    clave.length >= 6
        ) {
            Text("Registrarme", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onVolverLogin) {
            Text("Ya tengo cuenta, iniciar sesión", color = MaterialTheme.colorScheme.primary)
        }

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