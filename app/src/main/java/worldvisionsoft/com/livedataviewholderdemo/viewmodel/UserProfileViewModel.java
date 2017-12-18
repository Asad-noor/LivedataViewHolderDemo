package worldvisionsoft.com.livedataviewholderdemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import worldvisionsoft.com.livedataviewholderdemo.model.User;

/**
 * Created by user on 12/17/2017.
 */

public class UserProfileViewModel extends ViewModel{

    private LiveData<User> user;

    public LiveData<User> getUser() {
        return user;
    }
}
