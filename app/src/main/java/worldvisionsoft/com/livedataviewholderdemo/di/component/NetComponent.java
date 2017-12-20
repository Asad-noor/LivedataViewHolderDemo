package worldvisionsoft.com.livedataviewholderdemo.di.component;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import worldvisionsoft.com.livedataviewholderdemo.di.module.AppModule;
import worldvisionsoft.com.livedataviewholderdemo.di.module.NetModule;
import worldvisionsoft.com.livedataviewholderdemo.repo.DataRepository;
import worldvisionsoft.com.livedataviewholderdemo.util.App;
import worldvisionsoft.com.livedataviewholderdemo.viewmodel.UserProfileViewModel;

/**
 * Created by user on 12/17/2017.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(App app);
    void inject(UserProfileViewModel userProfileViewModel);
}
