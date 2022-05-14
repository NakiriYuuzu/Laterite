package tw.edu.pu.beacon

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class BeaconData(
    val minor: Int,
    var status: Boolean,
    val name: MutableState<String>
) {
    object BeaconArt03 : BeaconData(46404, true, mutableStateOf("Art01")) // 44B5
    object BeaconArt02 : BeaconData(37453, true, mutableStateOf("Art02")) // 4D92
    object BeaconArt01 : BeaconData(7860, true, mutableStateOf("Art03"))  // B41E
    object BeaconCafe : BeaconData(1445, true, mutableStateOf("Cofe"))    // A505
}
