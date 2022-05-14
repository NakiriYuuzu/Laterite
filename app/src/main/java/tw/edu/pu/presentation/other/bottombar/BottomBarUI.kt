package tw.edu.pu.presentation.other.bottombar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import tw.edu.pu.presentation.other.navigation.NavigationData
import tw.edu.pu.presentation.theme.ui.LateriteTheme


@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        NavigationData.ARCam,
        NavigationData.Home,
        NavigationData.Info,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
//  Rounded BottomNavigation
//        modifier = Modifier.graphicsLayer {
//            shape = RoundedCornerShape(
//                topStart = 20.dp,
//                topEnd = 20.dp
//            )
//            clip = true
//        },
        backgroundColor = LateriteTheme.colors.bottomBar
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavigationData,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = screen.icons,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}