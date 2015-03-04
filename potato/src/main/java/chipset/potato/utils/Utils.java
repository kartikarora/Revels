package chipset.potato.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Developer: chipset
 * Package : chipset.potato
 * Project : Potato-Library
 * Date : 14/1/15
 */
public class Utils {

    /**
     * Method to get internet connection status
     *
     * @param context Context of the current activity
     * @return true if internet connection is established else false
     */
    public boolean isInternetConnected(Context context) {
        boolean isConnected;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = (activeNetwork != null)
                && (activeNetwork.isConnectedOrConnecting());
        return isConnected;
    }

    /**
     * Method to hide keyboard
     *
     * @param context Context of the current activity
     * @param view view of the activity to get Window Token
     */

    public void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * Method to get Bluetooth status
     *
     * @param context Context of the current activity
     * @return true if internet bluetooth is enabled else false
     */
    public boolean isBluetoothOn(Context context) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(context, "Bluetooth not supported", Toast.LENGTH_SHORT).show();
            return false;
        }
        return mBluetoothAdapter.isEnabled();
    }
}