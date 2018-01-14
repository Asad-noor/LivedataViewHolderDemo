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

    private final WebService webservice;
    private final DataEntityDao dataEntityDao;
    //Gson gson;
    //LiveData<UserTable> data = null;

    @Inject
    public DataRepository(DataEntityDao dataEntityDao, WebService webservice){
        this.dataEntityDao = dataEntityDao;
        this.webservice = webservice;
    }

    public LiveData<Resource<UserTable>> loadUser(final String userId) {
        return new NetworkBoundResource<UserTable>() {
            @Override
            protected void saveCallResult(@NonNull Object item) {
                //dataEntityDao.save(item.getData());
                Log.d("tttt", "got some date to save on DB");
            }

            @Override
            protected boolean shouldFetch(@Nullable UserTable data) {
                //return rateLimiter.canFetch(userId) && (data == null || !isFresh(data));
                return true;
            }

            @NonNull @Override
            protected LiveData<UserTable> loadFromDb() {
                return dataEntityDao.load(userId);
            }

            @NonNull @Override
            protected Call<ApiResponse> createCall() {
                return webservice.testLogin("123456",
                    "61","124243434",
                    "dfbfdb", "dfbfdb",
                    "dfbdfb", "bfdbf","dfbfb");
            }

            @Override
            protected void onFetchFailed() {
                Log.d("tttt", "data fetch failed");
            }

        }.getAsLiveData();
    }
}
