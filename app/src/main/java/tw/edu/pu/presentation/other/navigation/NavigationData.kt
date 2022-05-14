package tw.edu.pu.presentation.other.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationData(
    val route: String,
    val title: String,
    val icons: ImageVector
) {
    object Home : NavigationData(
        route = "home",
        title = "Home",
        icons = Icons.Outlined.Home
    )

    object Info : NavigationData(
        route = "info",
        title = "Info",
        icons = Icons.Outlined.Info
    )

    object ARCam : NavigationData(
        route = "ar_cam",
        title = "AR_Cam",
        icons = Icons.Outlined.PhotoCamera
    )

    object Detail: NavigationData(
        route = "detail",
        title = "Detail",
        icons = Icons.Default.Details
    )

    object Direction: NavigationData(
        route = "direction",
        title = "Direction",
        icons = Icons.Default.Map
    )
}