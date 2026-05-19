package io.jadu.m3App

import androidx.compose.ui.window.ComposeUIViewController
import io.jadu.m3App.di.appModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }
