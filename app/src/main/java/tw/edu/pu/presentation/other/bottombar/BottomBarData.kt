package tw.edu.pu.presentation.other.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarData(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarData(
        route = "home",
        title = "Home",
        icon = Icons.Outlined.Home
    )

    object Info : BottomBarData(
        route = "info",
        title = "Info",
        icon = Icons.Outlined.Info
    )

    object ARCam : BottomBarData(
        route = "ar_cam",
        title = "AR_Cam",
        icon = Icons.Outlined.PhotoCamera
    )
}
