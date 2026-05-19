package io.jadu.m3App.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import coil3.Image
import io.jadu.m3App.ui.screens.BookingScreen
import io.jadu.m3App.ui.screens.FavoritesScreen
import io.jadu.m3App.ui.screens.HomeScreen
import io.jadu.m3App.ui.screens.ProfileScreen
import androidx.compose.material3.MaterialTheme
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass


private val NavBarSelected = Color(0xFFD4735C)
private val NavBarUnselected = Color(0xFFBBB5AF)
// NavBarSelected/Unselected kept as brand colors not in M3 scheme

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    val backStack = rememberNavBackStack(
        SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Screen.Home::class)
                    subclass(Screen.Bookings::class)
                    subclass(Screen.Favourites::class)
                    subclass(Screen.Profile::class)
                }
            }
        },
        Screen.Home
    )

    Scaffold (
        contentWindowInsets = WindowInsets(0),
        bottomBar = {
            CozyNavBar(
                currentScreen = currentScreen,
                onDestinationSelected = {currentScreen =  it}
            )
        }
    ){ paddingValues ->
        Crossfade(
            targetState = currentScreen,
            modifier = Modifier.padding(paddingValues)
        ) { screen ->
            when (screen) {
                is Screen.Home -> HomeScreen()
                is Screen.Bookings -> BookingScreen()
                is Screen.Favourites -> FavoritesScreen()
                is Screen.Profile -> ProfileScreen()
            }
        }
    }
}

private data class NavDestination(
    val screen: Screen,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

private val destinations = listOf(
    NavDestination(Screen.Home, "Home", Icons.Filled.Home, Icons.Outlined.Home),
    NavDestination(Screen.Bookings, "Bookings", Icons.Filled.Bookmark, Icons.Outlined.BookmarkBorder),
    NavDestination(Screen.Favourites, "Favorites", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder),
    NavDestination(Screen.Profile, "Profile", Icons.Filled.Person, Icons.Outlined.Person),
)






@Composable
private fun CozyNavBar(
    currentScreen: Screen,
    onDestinationSelected: (Screen) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 12.dp, shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            destinations.forEach { destination ->
                NavItem(
                    destination = destination,
                    selected = currentScreen == destination.screen,
                    onClick = { onDestinationSelected(destination.screen) },
                )
            }
        }
    }
}

@Composable
private fun NavItem(
    destination: NavDestination,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val iconColor by animateColorAsState(
        targetValue = if (selected) NavBarSelected else NavBarUnselected,
        animationSpec = spring(),
        label = "navIconColor",
    )
    val indicatorWidth by animateDpAsState(
        targetValue = if (selected) 56.dp else 40.dp,
        animationSpec = spring(dampingRatio = 0.6f, stiffness = 400f),
        label = "navIndicatorWidth",
    )
    val indicatorColor = MaterialTheme.colorScheme.primaryContainer

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick,
            )
            .padding(horizontal = 12.dp, vertical = 4.dp),
    ) {
        Box(
            modifier = Modifier
                .width(indicatorWidth)
                .height(36.dp)
                .clip(CircleShape)
                .background(if (selected) indicatorColor else Color.Transparent),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                contentDescription = destination.label,
                tint = iconColor,
                modifier = Modifier.size(22.dp),
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = destination.label,
            fontSize = 11.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = iconColor,
        )
    }
}




