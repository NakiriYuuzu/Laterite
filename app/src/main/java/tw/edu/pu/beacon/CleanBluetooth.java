package tw.edu.pu.beacon;

import androidx.activity.ComponentActivity;

import org.altbeacon.bluetooth.BluetoothMedic;

public class CleanBluetooth {
    public static void flushBluetooth(ComponentActivity context) {
        BluetoothMedic medic = BluetoothMedic.getInstance();
        medic.enablePowerCycleOnFailures(context);
        medic.enablePeriodicTests(context, BluetoothMedic.SCAN_TEST |
                BluetoothMedic.TRANSMIT_TEST);
    }
}
