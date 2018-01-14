package worldvisionsoft.com.livedataviewholderdemo.repo.api;

import android.annotation.SuppressLint;
import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by user on 9/24/2017.
 */

public class EncryptedKeyGenerator {

    private static final String ALGORITHM = "AES";
    private static final byte[] SALT = "tHeWorLD73774929".getBytes();// THE KEY MUST BE SAME

    public String getEncrypted(String plainText) {

        if (plainText == null) {
            return null;
        }

        Key salt = getSalt();

        try {
            @SuppressLint("GetInstance") Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, salt);
            byte[] encodedValue = cipher.doFinal(plainText.getBytes());
            return Base64.encodeToString(encodedValue, Base64.DEFAULT);
            //return new String(encodedValue, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Failed to encrypt data");
    }

    private Key getSalt() {
        return new SecretKeySpec(SALT, ALGORITHM);
    }
}
