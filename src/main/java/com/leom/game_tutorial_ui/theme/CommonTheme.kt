package com.leom.game_tutorial_ui.theme
import androidx.compose.foundation.Canvas
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// 커스텀 컬러 사용을 위한 확장 프로퍼티
object StarBattleTheme {
    val colors: CustomColorsPalette
        @Composable
        get() = LocalCustomColors.current
}

// Light Theme Colors
val LightBackground = Color(0xFFF5F4F8)
val LightSurfaceBackground = Color(0xFFDFDFE4)
val LightPrimary = Color(0xFFB349D1)
val LightPrimaryGradient = Color(0xFF935AFF)
val LightText = Color(0xFF000000)
val LightTextSecondary = Color(0xFF5E5E5E)
val LightSuccess = Color(0xFFC17F1D)
val LightSuccessBackground = Color(0xFFFDF3E5)
val LightError = Color(0xFFD6404F)
val LightErrorBackground = Color(0xFFFEE7E9)

// Dark Theme Colors
val DarkBackground = Color(0xFF1B1736)
val DarkSurfaceBackground = Color(0xFF2B2938)
val DarkPrimary = Color(0xFFB349D1)
val DarkPrimaryGradient = Color(0xFF935AFF)
val DarkText = Color(0xFFFFFFFF)
val DarkTextSecondary = Color(0xFFDBDBDB)
val DarkSuccess = Color(0xFFFFD484)
val DarkSuccessBackground = Color(0xFF332B1A)
val DarkError = Color(0xFFFF9494)
val DarkErrorBackground = Color(0xFF331F1F)

data class Borders(
    val top: Boolean = false,
    val right: Boolean = false,
    val bottom: Boolean = false,
    val left: Boolean = false
)

@Composable
fun BorderLines(
    borders: Borders,
    borderColor: Color,
    thinLineColor: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val strokeWidth = 4.5f

        // 기본 테두리 (얇은 선)
        if (!borders.top) {
            drawLine(
                color = thinLineColor,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 1f
            )
        }
        if (!borders.right) {
            drawLine(
                color = thinLineColor,
                start = Offset(size.width, 0f),
                end = Offset(size.width, size.height),
                strokeWidth = 1f
            )
        }
        if (!borders.bottom) {
            drawLine(
                color = thinLineColor,
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = 1f
            )
        }
        if (!borders.left) {
            drawLine(
                color = thinLineColor,
                start = Offset(0f, 0f),
                end = Offset(0f, size.height),
                strokeWidth = 1f
            )
        }

        // 영역 경계선 (굵은 선)
        if (borders.top) {
            drawLine(
                color = borderColor,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = strokeWidth
            )
        }
        if (borders.right) {
            drawLine(
                color = borderColor,
                start = Offset(size.width, 0f),
                end = Offset(size.width, size.height),
                strokeWidth = strokeWidth
            )
        }
        if (borders.bottom) {
            drawLine(
                color = borderColor,
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = strokeWidth
            )
        }
        if (borders.left) {
            drawLine(
                color = borderColor,
                start = Offset(0f, 0f),
                end = Offset(0f, size.height),
                strokeWidth = strokeWidth
            )
        }
    }
}

data class CustomColorsPalette(
    val background: Color,
    val backgroundGradient: Color,
    val primary: Color,
    val primaryGradient: Color,
    val secondary: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val success: Color,
    val successBackground: Color,
    val error: Color,
    val errorBackground: Color
)

val LightCustomColorsPalette = CustomColorsPalette(
    background = LightBackground,
    backgroundGradient = LightSurfaceBackground,
    primary = LightPrimary,
    primaryGradient = LightPrimaryGradient,
    secondary = LightPrimaryGradient,
    textPrimary = LightText,
    textSecondary = LightTextSecondary,
    success = LightSuccess,
    successBackground = LightSuccessBackground,
    error = LightError,
    errorBackground = LightErrorBackground
)

val DarkCustomColorsPalette = CustomColorsPalette(
    background = DarkBackground,
    backgroundGradient = DarkSurfaceBackground,
    primary = DarkPrimary,
    primaryGradient = DarkPrimaryGradient,
    secondary = DarkPrimaryGradient,
    textPrimary = DarkText,
    textSecondary = DarkTextSecondary,
    success = DarkSuccess,
    successBackground = DarkSuccessBackground,
    error = DarkError,
    errorBackground = DarkErrorBackground
)

val LocalCustomColors = staticCompositionLocalOf { LightCustomColorsPalette }

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    secondary = LightPrimaryGradient,
    background = LightBackground,
    surface = LightSurfaceBackground,
    onPrimary = LightText,
    onSecondary = LightText,
    onBackground = LightText,
    onSurface = LightTextSecondary
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkPrimaryGradient,
    background = DarkBackground,
    surface = DarkSurfaceBackground,
    onPrimary = DarkText,
    onSecondary = DarkText,
    onBackground = DarkText,
    onSurface = DarkTextSecondary
)

@Composable
fun StarBattleTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val customColorsPalette = if (darkTheme) DarkCustomColorsPalette else LightCustomColorsPalette

    CompositionLocalProvider(
        LocalCustomColors provides customColorsPalette
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}
