package tw.edu.pu.presentation.theme.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import tw.edu.pu.presentation.theme.*

private val DarkColorPalette = LateriteColors(
    bottomBar = grey,
    background = black,
    primaryText = white
)

private val LightColorPalette = LateriteColors(
    bottomBar = grey,
    background = white,
    primaryText = black
)

private val LocalLateriteColor = compositionLocalOf {
    LightColorPalette
}

object LateriteTheme {
    val colors: LateriteColors
        @Composable
        get() = LocalLateriteColor.current
    enum class Theme {
        Light, Dark
    }
}

@Stable
class LateriteColors(
    bottomBar: Color,
    background: Color,
    primaryText: Color

) {
    var bottomBar: Color by mutableStateOf(bottomBar)
        private set
    var background: Color by mutableStateOf(background)
        private set
    var primaryText: Color by mutableStateOf(primaryText)
        private set
}

@Composable
fun LateriteTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    CompositionLocalProvider(LocalLateriteColor provides colors) {
        MaterialTheme(
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}