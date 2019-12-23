package worldvisionsoft.com.livedataviewholderdemo.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

import android.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

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

        try {
            Log.d("tttt", ">>" + Encrypt("test text 123", "0123456789abcdef"));
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //addHomeFragment();
        //getUniqueID();
        //makeEncrypt();

        Observable<String> observable = Observable.just("toto", "goto");

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("tttt", "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d("tttt", "onNext >" + s);
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

    private void getUniqueID() {
        @SuppressLint("HardwareIds") String android_id = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Log.d("tttt", "ANDROID_ID >" + android_id);

//        TelephonyManager tm = (TelephonyManager) getBaseContext()
//                .getSystemService(MainActivity.TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//
//            String tmDevice = "" + tm.getDeviceId();
//            Log.d("tttt","DeviceIMEI >" + tmDevice);
//        }
    }

    private void makeEncrypt() {
        String data = "a4shTr7a011dUYTf"; // this data will be created by four randomly generated methods and
        //there will be four first character a s a d for eah one.
        final String enc = new EncryptedKeyGenerator().getEncrypted(data);
        Log.d("tttt", "value >" + enc);
    }

    private static String encode(String base64Text, String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // once, during initialization
        SecureRandom rand = new SecureRandom(); // or .getInstance* as you prefer

// unchanged
        SecretKeySpec key_spec = new SecretKeySpec("0123456789abcdef".getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        byte[] encoded_payload = Base64.encode("test text 123".getBytes(), Base64.DEFAULT);
// changed
        int block_size = cipher.getBlockSize();
// create random IV
        byte[] buffer = new byte[block_size];
        rand.nextBytes(buffer);
        IvParameterSpec iv = new IvParameterSpec (buffer);
// expand buffer already containing IV to make room for ciphertext
        buffer = Arrays.copyOf (buffer, block_size+encoded_payload.length);
// unchanged
        cipher.init(Cipher.ENCRYPT_MODE, key_spec, iv);
// changed
// do encryption into correct part of existing buffer
        try {
            cipher.doFinal(encoded_payload,0,encoded_payload.length, buffer,block_size);
            Log.d("tttt", "buf >" + encoded_payload);
            return Base64.encodeToString(buffer, Base64.DEFAULT);
            //return new String(buffer, StandardCharsets.UTF_8);
        } catch (ShortBufferException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Failed to encrypt data");
    }
}
