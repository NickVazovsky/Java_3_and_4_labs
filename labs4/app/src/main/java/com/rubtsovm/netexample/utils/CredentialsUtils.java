package com.rubtsovm.netexample.utils;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * CredentialsUtils Created by RubtsovM on 18.04.2018.
 */

public class CredentialsUtils {
    public final static String ts = "1";
    public final static String public_key = "de9b3451d70ed613b10be0a9d7787258";
    private final static String private_key = "b518b729f04bdbab3f99e5bb91cd07a572d1f863";

    public static String getHash(){
        String hash = ts+private_key+public_key;
        Log.i("CredentialsUtils",hash);
        return md5(hash);
    }

    private static String md5(String s) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
