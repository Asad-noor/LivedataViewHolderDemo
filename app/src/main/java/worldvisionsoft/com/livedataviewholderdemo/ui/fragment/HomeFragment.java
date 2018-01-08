package worldvisionsoft.com.livedataviewholderdemo.ui.fragment;

import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import worldvisionsoft.com.livedataviewholderdemo.R;
import worldvisionsoft.com.livedataviewholderdemo.viewmodel.UserProfileViewModel;

/**
 * Created by user on 12/17/2017.
 */

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UserProfileViewModel userProfileViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);

        if (savedInstanceState == null)
            userProfileViewModel.getUser(true);
        else
            userProfileViewModel.getUser(false);
    }
}
