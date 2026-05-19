package io.jadu.m3App

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import io.jadu.m3App.navigation.AppNavigation
import io.jadu.m3App.ui.theme.AppTheme

@Composable
@Preview
fun App() {
    var isDark by remember { mutableStateOf(false) }

    AppTheme(
        darkTheme = isDark,
        onToggleTheme = { isDark = !isDark }
    ) {
        AppNavigation()
    }
}
