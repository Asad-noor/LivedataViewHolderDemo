package worldvisionsoft.com.livedataviewholderdemo.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.dao.DataEntityDao;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.UserTable;
import worldvisionsoft.com.livedataviewholderdemo.repo.remote.WebService;
import worldvisionsoft.com.livedataviewholderdemo.repo.remote.model.ApiResponse;
import worldvisionsoft.com.livedataviewholderdemo.repo.remote.model.UsersDTO;
import worldvisionsoft.com.livedataviewholderdemo.util.App;
import worldvisionsoft.com.livedataviewholderdemo.util.General;

/**
 * Created by user on 12/17/2017.
 */

public class DataRepository {

    WebService webservice;
    DataEntityDao dataEntityDao;
    Gson gson;
    LiveData<UserTable> data = null;

    @Inject
    public DataRepository(DataEntityDao dataEntityDao, WebService webservice){
        this.dataEntityDao = dataEntityDao;
        this.webservice = webservice;
        gson = new Gson();
    }

    public LiveData<UserTable> testData(){

        if(General.isNetworkAvailable(App.getApp())){
            webservice.testLogin("123456",
                    "61","124243434",
                    "dfbfdb", "dfbfdb",
                    "dfbdfb", "bfdbf","dfbfb").enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    Log.d("tttt", "res >"+response.body().getMessage());
                    String jsonStr = gson.toJson(response.body());
                    UsersDTO usersList = gson.fromJson(jsonStr, UsersDTO.class);
                    //List<UserTable> users = usersList.getUsersList();
                    data = usersList.getUsersList();
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {

                }
            });
            return data;
        }else{
            return data;
        }
    }
}
