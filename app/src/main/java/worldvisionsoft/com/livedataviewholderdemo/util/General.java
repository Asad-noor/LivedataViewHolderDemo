package worldvisionsoft.com.livedataviewholderdemo.util;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by user on 12/18/2017.
 */

public class General {

    public static boolean isNetworkAvailable(Application application) {
        if (application != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) application.
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                return false;
            } else {
                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                return activeNetwork != null;
            }
        } else
            return false;
    }
}
