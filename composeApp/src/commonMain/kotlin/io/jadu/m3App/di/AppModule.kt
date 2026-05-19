package io.jadu.m3App.di

import io.jadu.m3App.ui.viewmodel.HomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun appModule(): Module = module {


    viewModel { HomeViewModel() }
}

val appModule = listOf(
    appModule(),
    platformModule()
)
