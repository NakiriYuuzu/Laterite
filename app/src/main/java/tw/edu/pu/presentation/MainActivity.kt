package tw.edu.pu.presentation

import android.Manifest
import android.bluetooth.BluetoothManager
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import org.altbeacon.beacon.Beacon
import org.altbeacon.beacon.Region
import tw.edu.pu.R
import tw.edu.pu.beacon.*
import tw.edu.pu.function.MusicPlayer
import tw.edu.pu.presentation.other.alert.CallAlertDialog
import tw.edu.pu.presentation.other.bottombar.BottomBar
import tw.edu.pu.presentation.other.navigation.Navigation
import tw.edu.pu.presentation.theme.ui.LateriteTheme

@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {

    private val beaconController = BeaconController()
    private val musicPlayer = MusicPlayer(this)

    private val TAG = "TAG"
    private var isPopUp = false
    private var isPlaying = false
    private var isClick = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beaconController.initBeaconManager(this)

        setContent {
            LateriteTheme {
                //FIXME Permission Setup
                val bluetoothManager =
                    this.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
                val mLocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val permissionsState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                    )
                )

                val isDialogOpen = remember { mutableStateOf(false) }
                val lifecycleOwner = LocalLifecycleOwner.current
                val currentSound = remember { mutableStateOf("") }

                DisposableEffect(
                    key1 = lifecycleOwner,
                    effect = {
                        val observer = LifecycleEventObserver { _, event ->
                            if (event == Lifecycle.Event.ON_START) {
                                permissionsState.launchMultiplePermissionRequest()
                            }
                        }
                        lifecycleOwner.lifecycle.addObserver(observer)

                        onDispose {
                            lifecycleOwner.lifecycle.removeObserver(observer)
                        }
                    }
                )

                //FIXME End Permission Setup

                Surface(
                    color = LateriteTheme.colors.background,
                ) {
                    CallAlertDialog(
                        isDialogOpen = isDialogOpen,
                        title = "請打開定位和藍牙才能體驗此App的完整性。",
                        onPositiveClick = {
                            isDialogOpen.value = false
                        },
                        onNegativeClick = {
                            isDialogOpen.value = false
                        }
                    )

                    if (!bluetoothManager.adapter.isEnabled || !mLocationManager.isProviderEnabled(
                            LocationManager.GPS_PROVIDER
                        )
                    ) {
                        isDialogOpen.value = true
                    }

                    val navController = rememberNavController()

                    Scaffold(bottomBar = { BottomBar(navController = navController) }) {
                        Column(modifier = Modifier.padding(bottom = 56.dp)) {
                            Navigation(navController = navController, this@MainActivity)
                        }
                    }
                }

                val popUpMenuCheck = remember { mutableStateOf(false) }
                val (isPlayClick, setCheck) = remember { mutableStateOf(true) }
                popUpMenuCheck.value = isPopUp

                BeaconScreen(
                    isPopUp = popUpMenuCheck,
                    title = "Hi",
                    image = R.drawable.cofe,
                    isPlayClick = isPlayClick,
                    onPlayButton = {
                        setCheck(!isPlayClick)
                        if (isClick) {
                            isClick = false
                            if (!isPlaying) {
                                if (currentSound.value == "Art01") {
                                    Log.e(TAG, "onCreate: ")
                                }
                                when (currentSound.value) {
                                    BeaconData.BeaconArt01.name.value -> {
                                        isPlaying = true
                                        musicPlayer.playMusic(R.raw.maple)
                                        Log.e(TAG, currentSound.value)
                                    }

                                    BeaconData.BeaconArt02.name.value -> {
                                        isPlaying = true
                                        musicPlayer.playMusic(R.raw.maple)
                                        Log.e(TAG, currentSound.value)
                                    }

                                    BeaconData.BeaconArt03.name.value -> {
                                        isPlaying = true
                                        musicPlayer.playMusic(R.raw.maple)
                                        Log.e(TAG, currentSound.value)
                                    }

                                    BeaconData.BeaconCafe.name.value -> {
                                        isPlaying = true
                                        musicPlayer.playMusic(R.raw.maple)
                                        Log.e(TAG, currentSound.value)
                                    }
                                }
                            }
                        } else {
                            isClick = true
                            if (isPlaying) {
                                isPlaying = false
                                musicPlayer.stopMusic()
                            }
                        }
                    },
                    onCloseButton = {
                        isClick = false
                        if (isPlaying) {
                            isPlaying = true
                            musicPlayer.stopMusic()
                        }
                        popUpMenuCheck.value = false
                    }
                )

                beaconController.onBeaconServiceConnect(object : BeaconController.BeaconModify {
                    override fun getBeacon(beacons: Collection<Beacon>, region: Region) {
                        if (beacons.isNotEmpty()) {
                            // init List
                            val list = mutableListOf<Beacon>()

                            // save Data to list
                            beacons.let {
                                for (beacon in beacons) {
                                    if (beacon.distance < 3f)
                                        list.add(beacon)
                                }
                            }

                            // sort list
                            if (list.size > 0) {
                                list.sortBy { it.distance }
                            }

                            Log.e(
                                TAG,
                                "isPopUp: $isPopUp   |   isClick: $isClick   |   firstBeacon: ${list[0].id3}"
                            )

                            if (!isPopUp) {
                                if (list.size > 0) {
                                    when (list[0].id3.toInt()) {
                                        BeaconData.BeaconArt01.minor -> {
                                            if (BeaconData.BeaconArt01.status) {
                                                BeaconData.BeaconArt01.status = false
                                                // TODO showDialog = true
                                                isPopUp = true
                                                popUpMenuCheck.value = isPopUp
                                                currentSound.value =
                                                    BeaconData.BeaconArt01.name.value

                                                Log.e(TAG, "Art1 = false")

                                                val handler = Handler(Looper.getMainLooper())
                                                handler.postDelayed({
                                                    BeaconData.BeaconArt02.status = true
                                                }, 300000)
                                            }
                                        }

                                        BeaconData.BeaconArt02.minor -> {
                                            if (BeaconData.BeaconArt02.status) {
                                                BeaconData.BeaconArt02.status = false
                                                // TODO showDialog = true
                                                isPopUp = true
                                                popUpMenuCheck.value = isPopUp
                                                currentSound.value =
                                                    BeaconData.BeaconArt02.name.value

                                                Log.e(TAG, "Art2 = false")

                                                val handler = Handler(Looper.getMainLooper())
                                                handler.postDelayed({
                                                    BeaconData.BeaconArt02.status = true
                                                }, 300000)
                                            }
                                        }

                                        BeaconData.BeaconArt03.minor -> {
                                            if (BeaconData.BeaconArt03.status) {
                                                BeaconData.BeaconArt03.status = false
                                                // TODO showDialog = true
                                                isPopUp = true
                                                popUpMenuCheck.value = isPopUp
                                                currentSound.value =
                                                    BeaconData.BeaconArt03.name.value

                                                Log.e(TAG, "Art3 = false")

                                                val handler = Handler(Looper.getMainLooper())
                                                handler.postDelayed({
                                                    BeaconData.BeaconArt03.status = true
                                                }, 300000)
                                            }
                                        }

                                        BeaconData.BeaconCafe.minor -> {
                                            if (BeaconData.BeaconCafe.status) {
                                                BeaconData.BeaconCafe.status = false
                                                // TODO showDialog = true
                                                isPopUp = true
                                                popUpMenuCheck.value = isPopUp
                                                currentSound.value =
                                                    BeaconData.BeaconCafe.name.value

                                                Log.e(TAG, "cofe = false")

                                                val handler = Handler(Looper.getMainLooper())
                                                handler.postDelayed({
                                                    BeaconData.BeaconCafe.status = true
                                                }, 300000)
                                            }
                                        }
                                    }
                                }
                            } else {
                                isPopUp = popUpMenuCheck.value

                                if (!isPopUp) {
                                    setCheck(isPlayClick)

                                    if (isPlaying) {
                                        isPlaying = false
                                        isClick = true
                                        musicPlayer.stopMusic()
                                    }
                                }
                            }
                        }
                    }
                })
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        beaconController.stopBeaconService()
        if (isPlaying) {
            musicPlayer.stopMusic()
        }
    }
}