package tw.edu.pu.beacon

import android.os.RemoteException
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import org.altbeacon.beacon.*

class BeaconController {
    private lateinit var beaconManager: BeaconManager

    private var once = false

    fun initBeaconManager(context: ComponentActivity) {
        beaconManager = BeaconManager.getInstanceForApplication(context)
        beaconManager.foregroundScanPeriod = 2000L
        if (!once) {
            beaconManager.beaconParsers.add(BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"))
            once = true
        }
//        beaconManager.beaconParsers.add(BeaconParser("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"))
    }

    fun onBeaconServiceConnect(beaconModify: BeaconModify) {
        beaconManager.removeAllMonitorNotifiers()
        beaconManager.addRangeNotifier(beaconModify::getBeacon)

        try {
            beaconManager.startRangingBeacons(Region("", null, null, null))
        } catch (e: RemoteException) {
        }
    }

    fun stopBeaconService() {
        beaconManager.removeAllRangeNotifiers()
        beaconManager.removeAllMonitorNotifiers()
    }

    interface BeaconModify {
        fun getBeacon(beacons: Collection<Beacon>, region: Region)
    }
}