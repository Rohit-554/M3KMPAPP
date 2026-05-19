package io.jadu.m3App

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import io.jadu.m3App.di.appModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(appModule)
    }

    val windowState = rememberWindowState(size = DpSize(1024.dp, 768.dp))

    Window(
        onCloseRequest = ::exitApplication,
        title = "M3 Expressive",
        state = windowState,
    ) {
        App()
    }
}