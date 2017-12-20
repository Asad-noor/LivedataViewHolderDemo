package worldvisionsoft.com.livedataviewholderdemo.util;

import android.app.Application;

import worldvisionsoft.com.livedataviewholderdemo.di.component.DaggerNetComponent;
import worldvisionsoft.com.livedataviewholderdemo.di.component.NetComponent;
import worldvisionsoft.com.livedataviewholderdemo.di.module.AppModule;
import worldvisionsoft.com.livedataviewholderdemo.di.module.NetModule;

/**
 * Created by user on 12/18/2017.
 */

public class App extends Application{

    private static App mInstance;
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
        mNetComponent.inject(this);
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
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
