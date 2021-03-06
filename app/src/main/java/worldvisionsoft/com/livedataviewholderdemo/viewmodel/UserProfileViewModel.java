package worldvisionsoft.com.livedataviewholderdemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import worldvisionsoft.com.livedataviewholderdemo.repo.DataRepository;
import worldvisionsoft.com.livedataviewholderdemo.repo.Resource;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.Posts;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.UserTable;
import worldvisionsoft.com.livedataviewholderdemo.util.App;

/**
 * Created by user on 12/17/2017.
 */

public class UserProfileViewModel extends ViewModel {

    @Inject
    DataRepository dataRepository;
    LiveData<Resource<Posts>> data;

    public UserProfileViewModel() {
        App.getApp().getNetComponent().inject(this);
        //if we need only once when the app starts after killing it.
        //dataRepository.testData();
    }

    public LiveData<Resource<Posts>> getUser(boolean isNeedToLoad) {
        //if we need each time the screen loads and onCreate called.
        if (isNeedToLoad) {
            data = dataRepository.loadUser();
            return data;
        } else {
            return data;
        }
    }
}
