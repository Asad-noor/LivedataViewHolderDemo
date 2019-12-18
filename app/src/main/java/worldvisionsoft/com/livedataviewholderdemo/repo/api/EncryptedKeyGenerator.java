package worldvisionsoft.com.livedataviewholderdemo.repo.api;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by user on 9/24/2017.
 */

public class EncryptedKeyGenerator {

    private static final String ALGORITHM = "AES";
    private static byte[] SALT;// THE KEY MUST BE SAME



    public String getEncrypted(String plainText) {

        if (plainText == null) {
            return null;
        }

        Key salt = getSalt();

        try {
            @SuppressLint("GetInstance") Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");

            //int blockSize = cipher.getBlockSize();
            //IvParameterSpec iv = new IvParameterSpec(Arrays.copyOf(plainText.getBytes(), blockSize));
            //byte[] dataToEncrypt = Arrays.copyOfRange(plainText.getBytes(), blockSize, plainText.length());

            cipher.init(Cipher.ENCRYPT_MODE, salt);
            byte[] encodedValue = cipher.doFinal(plainText.getBytes());
            //return new String(encodedValue, StandardCharsets.UTF_8);

            return Base64.encodeToString(encodedValue, Base64.DEFAULT);
            //return new String(encodedValue, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Failed to encrypt data");
    }

    private Key getSalt() {
        return new SecretKeySpec("0123456789abcdef".getBytes(), ALGORITHM);
    }
}
