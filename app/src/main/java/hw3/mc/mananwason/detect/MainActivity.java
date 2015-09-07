package hw3.mc.mananwason.detect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Manan Wason on 04/09/15.
 */
public class MainActivity extends AppCompatActivity {

    IntentFilter filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(AudioManager.RINGER_MODE_CHANGED_ACTION);
        this.registerReceiver(mBroadcastReceiver, filter);

    }

    private void displayAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setCancelable(
                false).setPositiveButton(getString(R.string.dismiss_dialog),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(Intent.ACTION_BATTERY_LOW)) {
                displayAlert(getString(R.string.battery_low));
            }
            if (action.equals(AudioManager.RINGER_MODE_CHANGED_ACTION)) {
                displayAlert(getString(R.string.ringer_changed));
            }
            if (action.equals(Intent.ACTION_POWER_CONNECTED)) {
                displayAlert(getString(R.string.power_connected));
            }
            if (action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
                displayAlert(getString(R.string.power_disconnected));
            }
            if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
                displayAlert(getString(R.string.wifi_state_changed));
            }
            if (action.equals(Intent.ACTION_BATTERY_OKAY)) {
                displayAlert(getString(R.string.battery_okay));
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        this.registerReceiver(mBroadcastReceiver, filter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);

    }
}