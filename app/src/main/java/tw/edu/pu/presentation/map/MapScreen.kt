package tw.edu.pu.presentation.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import tw.edu.pu.presentation.theme.ui.LateriteTheme

@Composable
fun MapScreen(
    navController: NavController?,
    place: String?
) {
    val scaffoldState = rememberScaffoldState()
    var location = remember { LatLng(24.225888202079016, 120.57717908282856) }
    var cameraPositionState: CameraPositionState

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController?.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "ExitMap",
                    tint = LateriteTheme.colors.primaryText
                )
            }
        }
    ) {
        when (place?.toInt()) {
            1 -> {
                location = LatLng(24.225888202079016, 120.57717908282856)
            }

            2 -> {
                location = LatLng(24.225888202079016, 120.57717908282856)
            }

            3 -> {
                location = LatLng(24.225888202079016, 120.57717908282856)

            }
        }
        ShowGoogleMap(location = location)
    }
}

@Composable
fun ShowGoogleMap(
    location: LatLng
) {
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        uiSettings = remember {  MapUiSettings(zoomControlsEnabled = false) },
    ) {
        Marker(
            position = location
        )

        val list = mutableListOf<LatLng>()
        list.add(location)
        list.add(LatLng(24.01, -120.222))

        Polyline(
            points = list
        )
    }
}