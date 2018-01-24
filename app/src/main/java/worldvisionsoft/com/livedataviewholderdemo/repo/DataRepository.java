package worldvisionsoft.com.livedataviewholderdemo.repo;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import javax.inject.Inject;

import retrofit2.Call;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.dao.DataEntityDao;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.Posts;
import worldvisionsoft.com.livedataviewholderdemo.repo.remote.WebService;

/**
 * Created by user on 12/17/2017.
 */

public class DataRepository {

    private final WebService webservice;
    private final DataEntityDao dataEntityDao;
    //Gson gson;

    @Inject
    public DataRepository(DataEntityDao dataEntityDao, WebService webservice){
        this.dataEntityDao = dataEntityDao;
        this.webservice = webservice;
    }

    public LiveData<Resource<Posts>> loadUser() {
        return new NetworkBoundResource<Posts>() {
            @Override
            protected void saveCallResult(@NonNull Posts item) {
                dataEntityDao.savePosts(item);
                Log.d("tttt", "got some date to save on DB");
            }

            @Override
            protected boolean shouldFetch(@Nullable Posts data) {
                //return rateLimiter.canFetch(userId) && (data == null || !isFresh(data));
                Log.d("tttt", "data shouldFetch >"+data);
                return data == null;
            }

            @NonNull @Override
            protected LiveData<Posts> loadFromDb() {
                return dataEntityDao.getAllPosts();
            }

            @NonNull @Override
            protected Call<Posts> createCall() {
                return webservice.testLogin("title1", "body1", 11);
            }

            @Override
            protected void onFetchFailed() {
                Log.d("tttt", "data fetch failed");
            }

        }.getAsLiveData();
    }
}
