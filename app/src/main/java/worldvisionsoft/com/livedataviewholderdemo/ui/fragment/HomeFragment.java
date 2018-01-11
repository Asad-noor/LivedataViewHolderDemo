package worldvisionsoft.com.livedataviewholderdemo.ui.fragment;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import worldvisionsoft.com.livedataviewholderdemo.R;
import worldvisionsoft.com.livedataviewholderdemo.repo.Resource;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.UserTable;
import worldvisionsoft.com.livedataviewholderdemo.viewmodel.UserProfileViewModel;

/**
 * Created by user on 12/17/2017.
 */

public class HomeFragment extends BaseFragment {

    private Observer<Resource<UserTable>> observer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLifecycle().addObserver(new HomeLifecycleObserver());

        UserProfileViewModel userProfileViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);

        observer = userTableResource -> {
            Log.d("tttt", "current state is created");
        };

        if (savedInstanceState == null)
            userProfileViewModel.getUser(true).observe(this, observer);
        else
            userProfileViewModel.getUser(false);
    }

    private class HomeLifecycleObserver implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void onCreate() {
            Log.d("tttt", "ON_CREATE");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onStart() {
            Log.d("tttt", "ON_START");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void onStop() {
            Log.d("tttt", "ON_STOP");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause() {
            Log.d("tttt", "ON_PAUSE");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume() {
            Log.d("tttt", "ON_RESUME");
        }
    }
}
