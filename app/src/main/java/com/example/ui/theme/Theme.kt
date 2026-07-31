package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = GoldAccent,
    onPrimary = Color.Black,
    primaryContainer = Amber900,
    onPrimaryContainer = Amber100,
    secondary = Amber600,
    onSecondary = Color.White,
    background = Color(0xFF1C1917),
    onBackground = Amber100,
    surface = Color(0xFF292524),
    onSurface = Amber100,
    surfaceVariant = Color(0xFF44403C),
    onSurfaceVariant = Amber100
)

private val LightColorScheme = lightColorScheme(
    primary = Amber700,
    onPrimary = Color.White,
    primaryContainer = Amber100,
    onPrimaryContainer = Amber900,
    secondary = Amber600,
    onSecondary = Color.White,
    background = Amber50,
    onBackground = Color(0xFF292524),
    surface = Color.White,
    onSurface = Color(0xFF292524),
    surfaceVariant = Color(0xFFFEF3C7),
    onSurfaceVariant = Amber900
)

@Composable
fun BCTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
