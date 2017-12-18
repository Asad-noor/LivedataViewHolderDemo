package worldvisionsoft.com.livedataviewholderdemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by user on 12/18/2017.
 */

public class General {

    public static boolean isNetworkAvailable(Context context, boolean isNeedToNotify) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                //if (isNeedToNotify)
                    //
                return false;
            } else {
                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                if (activeNetwork != null) { //connected to the internet.
                    return true;
                }
            }
            //if (isNeedToNotify)
                //

            return false;
        } else
            return false;
    }
}
