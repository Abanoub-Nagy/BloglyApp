package com.example.bloglyapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.androidblogs.presentation.theme.backgroundDark
import com.example.androidblogs.presentation.theme.backgroundLight
import com.example.androidblogs.presentation.theme.errorContainerDark
import com.example.androidblogs.presentation.theme.errorContainerLight
import com.example.androidblogs.presentation.theme.errorDark
import com.example.androidblogs.presentation.theme.errorLight
import com.example.androidblogs.presentation.theme.inverseOnSurfaceDark
import com.example.androidblogs.presentation.theme.inverseOnSurfaceLight
import com.example.androidblogs.presentation.theme.inversePrimaryDark
import com.example.androidblogs.presentation.theme.inversePrimaryLight
import com.example.androidblogs.presentation.theme.inverseSurfaceDark
import com.example.androidblogs.presentation.theme.inverseSurfaceLight
import com.example.androidblogs.presentation.theme.onBackgroundDark
import com.example.androidblogs.presentation.theme.onBackgroundLight
import com.example.androidblogs.presentation.theme.onErrorContainerDark
import com.example.androidblogs.presentation.theme.onErrorContainerLight
import com.example.androidblogs.presentation.theme.onErrorDark
import com.example.androidblogs.presentation.theme.onErrorLight
import com.example.androidblogs.presentation.theme.onPrimaryContainerDark
import com.example.androidblogs.presentation.theme.onPrimaryContainerLight
import com.example.androidblogs.presentation.theme.onPrimaryDark
import com.example.androidblogs.presentation.theme.onPrimaryLight
import com.example.androidblogs.presentation.theme.onSecondaryContainerDark
import com.example.androidblogs.presentation.theme.onSecondaryContainerLight
import com.example.androidblogs.presentation.theme.onSecondaryDark
import com.example.androidblogs.presentation.theme.onSecondaryLight
import com.example.androidblogs.presentation.theme.onSurfaceDark
import com.example.androidblogs.presentation.theme.onSurfaceLight
import com.example.androidblogs.presentation.theme.onSurfaceVariantDark
import com.example.androidblogs.presentation.theme.onSurfaceVariantLight
import com.example.androidblogs.presentation.theme.onTertiaryContainerDark
import com.example.androidblogs.presentation.theme.onTertiaryContainerLight
import com.example.androidblogs.presentation.theme.onTertiaryDark
import com.example.androidblogs.presentation.theme.onTertiaryLight
import com.example.androidblogs.presentation.theme.outlineDark
import com.example.androidblogs.presentation.theme.outlineLight
import com.example.androidblogs.presentation.theme.outlineVariantDark
import com.example.androidblogs.presentation.theme.outlineVariantLight
import com.example.androidblogs.presentation.theme.primaryContainerDark
import com.example.androidblogs.presentation.theme.primaryContainerLight
import com.example.androidblogs.presentation.theme.primaryDark
import com.example.androidblogs.presentation.theme.primaryLight
import com.example.androidblogs.presentation.theme.scrimDark
import com.example.androidblogs.presentation.theme.scrimLight
import com.example.androidblogs.presentation.theme.secondaryContainerDark
import com.example.androidblogs.presentation.theme.secondaryContainerLight
import com.example.androidblogs.presentation.theme.secondaryDark
import com.example.androidblogs.presentation.theme.secondaryLight
import com.example.androidblogs.presentation.theme.surfaceBrightDark
import com.example.androidblogs.presentation.theme.surfaceBrightLight
import com.example.androidblogs.presentation.theme.surfaceContainerDark
import com.example.androidblogs.presentation.theme.surfaceContainerHighDark
import com.example.androidblogs.presentation.theme.surfaceContainerHighLight
import com.example.androidblogs.presentation.theme.surfaceContainerHighestDark
import com.example.androidblogs.presentation.theme.surfaceContainerHighestLight
import com.example.androidblogs.presentation.theme.surfaceContainerLight
import com.example.androidblogs.presentation.theme.surfaceContainerLowDark
import com.example.androidblogs.presentation.theme.surfaceContainerLowLight
import com.example.androidblogs.presentation.theme.surfaceContainerLowestDark
import com.example.androidblogs.presentation.theme.surfaceContainerLowestLight
import com.example.androidblogs.presentation.theme.surfaceDark
import com.example.androidblogs.presentation.theme.surfaceDimDark
import com.example.androidblogs.presentation.theme.surfaceDimLight
import com.example.androidblogs.presentation.theme.surfaceLight
import com.example.androidblogs.presentation.theme.surfaceVariantDark
import com.example.androidblogs.presentation.theme.surfaceVariantLight
import com.example.androidblogs.presentation.theme.tertiaryContainerDark
import com.example.androidblogs.presentation.theme.tertiaryContainerLight
import com.example.androidblogs.presentation.theme.tertiaryDark
import com.example.androidblogs.presentation.theme.tertiaryLight

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

@Composable
fun BloglyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true, content: @Composable() () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme, typography = AppTypography, content = content
    )
}

