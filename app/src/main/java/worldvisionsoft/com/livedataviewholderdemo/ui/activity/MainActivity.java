package worldvisionsoft.com.livedataviewholderdemo.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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
