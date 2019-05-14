package za.co.lbnkosi.bidorbuyassesment.application.presentation.utils.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Lebogang Nkosi on 26/04/2019.
 */

public class NetworkHelper {

    private Context context;

    public NetworkHelper(Context context) {
        this.context = context;
    }


    /**
     * Simple method to check if the device has an active internet connection
     */
    public boolean isThereInternetConnection() {
        boolean isConnected;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());
        return isConnected;
    }

}
