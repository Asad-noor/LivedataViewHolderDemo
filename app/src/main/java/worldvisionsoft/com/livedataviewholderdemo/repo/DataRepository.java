package worldvisionsoft.com.livedataviewholderdemo.repo;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.dao.DataEntityDao;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.UserTable;
import worldvisionsoft.com.livedataviewholderdemo.repo.remote.WebService;
import worldvisionsoft.com.livedataviewholderdemo.repo.remote.model.ApiResponse;
import worldvisionsoft.com.livedataviewholderdemo.util.App;
import worldvisionsoft.com.livedataviewholderdemo.util.General;

/**
 * Created by user on 12/17/2017.
 */

public class DataRepository {

    WebService webservice;
    DataEntityDao dataEntityDao;
    Gson gson;

    public DataRepository(DataEntityDao dataEntityDao, WebService webservice){
        this.dataEntityDao = dataEntityDao;
        this.webservice = webservice;
        gson = new Gson();
    }

    public LiveData<List<UserTable>> getUsers(){
        if(General.isNetworkAvailable(App.getApp(), false)){

            final LiveData<List<UserTable>> users = null;
            webservice.loadMovies().enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    String jsonStr = gson.toJson(response.body().getData());
                    //List<UserTable> usersList = gson.fromJson(jsonStr, UserTable.class);
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {

                }
            });
            return users;
        }else{
            return dataEntityDao.getAllUsers();
        }
    }
}
