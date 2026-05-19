package io.jadu.m3App.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Violet10 = Color(0xFF1A0036)
val Violet20 = Color(0xFF2D0060)
val Violet30 = Color(0xFF430090)
val Violet40 = Color(0xFF5C00BF)
val Violet80 = Color(0xFFCB9EFF)
val Violet90 = Color(0xFFEBD6FF)
val Violet95 = Color(0xFFF8EEFF)

val Teal20 = Color(0xFF003733)
val Teal30 = Color(0xFF00504A)
val Teal40 = Color(0xFF006B63)
val Teal80 = Color(0xFF4DDBD0)
val Teal90 = Color(0xFF70F7EB)
val Teal95 = Color(0xFFB3FFF8)

val Rose40 = Color(0xFFBA1A1A)
val Rose80 = Color(0xFFFFB4AB)
val Rose90 = Color(0xFFFFDAD6)

val Neutral10 = Color(0xFF1B1B1F)
val Neutral20 = Color(0xFF303034)
val Neutral90 = Color(0xFFE4E1EC)
val Neutral95 = Color(0xFFF3EFF9)
val Neutral99 = Color(0xFFFFFBFF)

val NeutralVariant30 = Color(0xFF4A4458)
val NeutralVariant50 = Color(0xFF7A7289)
val NeutralVariant60 = Color(0xFF958FA3)
val NeutralVariant80 = Color(0xFFCBC4D9)
val NeutralVariant90 = Color(0xFFE7DFF6)

// Brand colors (not part of M3 scheme)
val Coral = Color(0xFFD4735C)
val Sage = Color(0xFF6B7B4F)

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2C1810),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFF5EDD8),
    onPrimaryContainer = Color(0xFF2C1810),
    secondary = Teal40,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFF5EDD8),
    onSecondaryContainer = Teal20,
    tertiary = Violet30,
    onTertiary = Color.White,
    tertiaryContainer = Violet90,
    onTertiaryContainer = Violet10,
    error = Rose40,
    onError = Color.White,
    errorContainer = Rose90,
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFFAF6F0),
    onBackground = Color(0xFF2C1810),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF2C1810),
    surfaceVariant = Color(0xFFFAF6F0),
    onSurfaceVariant = Color(0xFF9E9E9E),
    outline = Color(0xFF9E9E9E),
    outlineVariant = NeutralVariant80,
    inverseSurface = Neutral20,
    inverseOnSurface = Neutral95,
    inversePrimary = Violet80,
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFC107),
    onPrimary = Color(0xFF1A1310),
    primaryContainer = Color(0xFF2E2416),
    onPrimaryContainer = Color(0xFFF5E6DC),
    secondary = Teal80,
    onSecondary = Teal20,
    secondaryContainer = Color(0xFF2E2416),
    onSecondaryContainer = Teal90,
    tertiary = Violet80,
    onTertiary = Violet20,
    tertiaryContainer = Violet30,
    onTertiaryContainer = Violet90,
    error = Rose80,
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Rose90,
    background = Color(0xFF1A1310),
    onBackground = Color(0xFFF5E6DC),
    surface = Color(0xFF2A2220),
    onSurface = Color(0xFFF5E6DC),
    surfaceVariant = Color(0xFF332926),
    onSurfaceVariant = Color(0xFF9E9E9E),
    outline = Color(0xFF9E9E9E),
    outlineVariant = NeutralVariant30,
    inverseSurface = Neutral90,
    inverseOnSurface = Neutral20,
    inversePrimary = Violet40,
)
