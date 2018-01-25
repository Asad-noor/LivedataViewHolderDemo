package worldvisionsoft.com.livedataviewholderdemo.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import worldvisionsoft.com.livedataviewholderdemo.R;
import worldvisionsoft.com.livedataviewholderdemo.repo.api.EncryptedKeyGenerator;
import worldvisionsoft.com.livedataviewholderdemo.ui.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addHomeFragment();
        makeEncrypt();

        Observable<String> observable = Observable.just("toto", "goto");

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("tttt", "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d("tttt", "onNext >"+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("tttt", "onError");
            }

            @Override
            public void onComplete() {
                Log.d("tttt", "onComplete");
            }
        };
        observable.doOnNext(c -> Log.d("tttt", "do on Next"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    private void addHomeFragment() {
        Fragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, homeFragment, "home").commitAllowingStateLoss();
    }

    private void makeEncrypt() {
        String data = "a4shTr7a011dUYTf"; // this data will be created by four randomly generated methods and
        //there will be four first character a s a d for eah one.
        final String enc = new EncryptedKeyGenerator().getEncrypted(data);
        Log.d("tttt", "value >"+enc);
    }
}
