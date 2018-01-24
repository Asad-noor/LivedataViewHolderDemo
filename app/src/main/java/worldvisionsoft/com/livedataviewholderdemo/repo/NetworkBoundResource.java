package worldvisionsoft.com.livedataviewholderdemo.repo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import org.reactivestreams.Subscriber;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.entity.Posts;


/**
 * Created by user on 1/10/2018.
 */

public abstract class NetworkBoundResource<ResultType> {
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    NetworkBoundResource() {
        result.setValue(Resource.loading(null));
        LiveData<ResultType> dbSource = loadFromDb();

        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);

            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> result.setValue(Resource.success(newData)));
            }
        });
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        Log.d("tttt", "fetchFromNetwork called");
        result.addSource(dbSource, newData -> result.setValue(Resource.loading(newData)));

        createCall().enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                result.removeSource(dbSource);
                Log.d("tttt", "got respo >"+response.body().getBody());
                saveResultAndReInit(response.body());
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {
                Log.d("tttt", "data load failed >"+t.getMessage());
                onFetchFailed();
                result.removeSource(dbSource);
                result.addSource(dbSource, newData -> result.setValue(Resource.error(t.getMessage(), newData)));
            }
        });
    }

    @MainThread
    private void saveResultAndReInit(Posts response) {
        Flowable<Posts> objectFlowable = Flowable.just(response);

        Subscriber<Posts> subscriber = new DisposableSubscriber<Posts>() {
            @Override
            public void onNext(Posts s) {
                Log.d("tttt", " onNext >"+s.getTitle());
                saveCallResult(s);
            }

            @Override
            public void onError(Throwable t) {
                Log.d("tttt", " onError");
            }

            @Override
            public void onComplete() {
                Log.d("tttt", " onComplete");
                result.addSource(loadFromDb(),
                        newData -> result.setValue(Resource.success(newData)));
            }
        };

        objectFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(subscriber);
    }

    // Called to save the result of the API response into the database
    @WorkerThread
    protected abstract void saveCallResult(@NonNull Posts item);

    // Called with the data in the database to decide whether it should be
    // fetched from the network.
    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    // Called to get the cached data from the database
    @NonNull @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    // Called to create the API call.
    @NonNull @MainThread
    protected abstract Call<Posts> createCall();

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    @MainThread
    protected abstract void onFetchFailed();

    // returns a LiveData that represents the resource, implemented
    // in the base class.
    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }
}
