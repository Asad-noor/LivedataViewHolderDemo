package worldvisionsoft.com.livedataviewholderdemo.ui.fragment;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.Observer;
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

        UserProfileViewModel userProfileViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);

        observer = userTableResource -> {
            if(super.getCurrentState().equals(Lifecycle.State.CREATED)){
                Log.d("tttt", "current state is created");
            }
        };

        if (savedInstanceState == null)
            userProfileViewModel.getUser(true).observe(this, observer);
        else
            userProfileViewModel.getUser(false);
    }


}
