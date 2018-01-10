package worldvisionsoft.com.livedataviewholderdemo.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import worldvisionsoft.com.livedataviewholderdemo.BuildConfig;
import worldvisionsoft.com.livedataviewholderdemo.repo.DataRepository;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.MyDatabase;
import worldvisionsoft.com.livedataviewholderdemo.repo.local.dao.DataEntityDao;
import worldvisionsoft.com.livedataviewholderdemo.repo.remote.WebService;
import worldvisionsoft.com.livedataviewholderdemo.util.Constants;


/**
 * Created by user on 12/17/2017.
 */
@Module
public class NetModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

//    @Provides
//    @Singleton
//    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
//        HttpLoggingInterceptor httpLoggingInterceptor =
//                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                    @Override
//                    public void log(String message) {
//                        Log.d("tttt", "message HttpLoggingInterceptor >" + message);
//                    }
//                });
//        httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? HEADERS : NONE);
//        return httpLoggingInterceptor;
//    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient() {
        return new OkHttpClient.Builder()
                //.addInterceptor(provideHttpLoggingInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .baseUrl(Constants.BASE_URL)
                .client(provideOkhttpClient())
                .build();
    }

    @Provides
    @Singleton
    WebService provideRetrofitToWeb() {
        Retrofit retrofit = provideRetrofit();
        return retrofit.create(WebService.class);
    }

    @Provides
    @Singleton
    MyDatabase provideMovieDatabase(Application application) {
        return Room.databaseBuilder(application, MyDatabase.class, "mydata.db").build();
    }

    @Provides
    @Singleton
    DataEntityDao provideMovieDao(MyDatabase myDatabase) {
        return myDatabase.userDao();
    }

    @Provides
    @Singleton
    DataRepository provideRepository(Application application){
        return new DataRepository(provideMovieDao(provideMovieDatabase(application)), provideRetrofitToWeb());
    }
}
