package worldvisionsoft.com.livedataviewholderdemo.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import worldvisionsoft.com.livedataviewholderdemo.R;
import worldvisionsoft.com.livedataviewholderdemo.ui.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addHomeFragment();
    }

    private void addHomeFragment() {
        Fragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, homeFragment, "home").commitAllowingStateLoss();
    }
}
