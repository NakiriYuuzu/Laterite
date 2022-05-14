package tw.edu.pu.presentation.other.navigation

import androidx.compose.runtime.Composable
import androidx.core.app.ComponentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tw.edu.pu.presentation.detail.DetailScreen
import tw.edu.pu.presentation.home.HomeScreen
import tw.edu.pu.presentation.info.InfoScreen
import tw.edu.pu.presentation.map.MapScreen
import tw.edu.pu.presentation.other.bottombar.BottomBarData

@Composable
fun Navigation(navController: NavHostController, context: ComponentActivity) {
    NavHost(
        navController = navController,
        startDestination = BottomBarData.Info.route
    ) {
        composable(NavigationData.Home.route) {
            HomeScreen(navController = navController, context)
        }

        composable(NavigationData.Info.route) {
            InfoScreen(navController = navController)
        }

        composable(NavigationData.ARCam.route) {
//            val isDialogOpen =
//            CallAlertDialog(
//                isDialogOpen = false,
//                title = ,
//                onPositiveClick = {  }) {
//
//            }
        }

        composable(NavigationData.Detail.route + "/{id}") { navBackStack ->
            val place = navBackStack.arguments?.getString("id")
            DetailScreen(navController = navController, place = place)
        }

        composable(NavigationData.Direction.route + "/{id}") { navBackStack ->
            val place = navBackStack.arguments?.getString("id")
            MapScreen(navController = navController, place = place)
        }
    }
}