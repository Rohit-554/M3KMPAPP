package io.jadu.m3App.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey {
    @Serializable
    data object Home : Screen

    @Serializable
    data object Bookings: Screen

    @Serializable
    data object Favourites: Screen

    @Serializable
    data object Profile: Screen
}