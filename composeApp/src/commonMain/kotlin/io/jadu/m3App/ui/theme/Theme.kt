package io.jadu.m3App.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

val LocalThemeToggle = compositionLocalOf<() -> Unit> { {} }
val LocalIsDarkTheme = compositionLocalOf { false }

@Composable
fun AppTheme(
    darkTheme: Boolean = false,
    onToggleTheme:() -> Unit = {},
    content: @Composable () -> Unit
){
    val colorScheme = if(darkTheme) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalThemeToggle provides onToggleTheme,
        LocalIsDarkTheme provides darkTheme
    ) {
        MaterialTheme (
            colorScheme = colorScheme,
            typography = appTypography(),
            content = content
        )
    }
}