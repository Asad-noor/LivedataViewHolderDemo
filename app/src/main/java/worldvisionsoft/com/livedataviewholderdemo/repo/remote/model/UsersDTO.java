package worldvisionsoft.com.livedataviewholderdemo.repo.remote.model;

import android.arch.lifecycle.LiveData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.UserTable;

/**
 * Created by user on 12/18/2017.
 */

public class UsersDTO {

    @SerializedName("List")
    private LiveData<UserTable> usersList;

    public LiveData<UserTable> getUsersList() {
        return usersList;
    }

    public void setUsersList(LiveData<UserTable> usersList) {
        this.usersList = usersList;
    }
}
