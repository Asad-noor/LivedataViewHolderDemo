package worldvisionsoft.com.livedataviewholderdemo.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import worldvisionsoft.com.livedataviewholderdemo.R;
import worldvisionsoft.com.livedataviewholderdemo.viewmodel.UserProfileViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserProfileViewModel userProfileViewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);

        if(savedInstanceState == null)
            userProfileViewModel.getUser(true);
        else
            userProfileViewModel.getUser(false);
    }

    public void openApp(View view) {
        Intent intent = new Intent (Intent.ACTION_VIEW);
        intent.setData (Uri.parse("usuaccess://splash"));
        startActivity(intent);
    }
}
