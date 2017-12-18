package worldvisionsoft.com.livedataviewholderdemo.util;

import android.app.Application;

/**
 * Created by user on 12/18/2017.
 */

public class App extends Application{

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static App getApp() {
        if (mInstance != null) {
            return mInstance;
        } else {
            mInstance = new App();
            mInstance.onCreate();
            return mInstance;
        }
    }
}
