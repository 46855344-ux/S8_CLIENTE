package com.example.crocanti_chiken_s5.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = NaranjaCrocanti,
    secondary = AmarilloDorado,
    tertiary = RojoPromo,
    background = CremaFondo,
    surface = Blanco,
    onPrimary = Blanco,
    onBackground = NegroTexto,
    onSurface = NegroTexto
)

@Composable
fun CrocantiTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}