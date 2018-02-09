package com.raqust.bluko.common.utils;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by linzehao
 * time: 2018/2/8.
 * info:
 */

public class RsaEncrypt {

    public static final String RSA = "RSA";
    /**加密方式，android的*/
// public static final String TRANSFORMATION = "RSA/None/NoPadding";
    /**加密方式，标准jdk的*/
    public static final String TRANSFORMATION = "RSA/None/PKCS1Padding";

    /** 使用公钥加密 */
    public static String encryptByPublicKey(String dataString, String publicKeyString) throws Exception {
        byte[] data  =dataString.getBytes("UTF-8");
        byte[] publicKey  =Base64.decode(publicKeyString,Base64.NO_WRAP);
        // 得到公钥对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        // 加密数据
        Cipher cp = Cipher.getInstance(TRANSFORMATION);
        cp.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeToString(cp.doFinal(data),Base64.NO_WRAP);
    }

    /** 使用私钥解密 */
    public static String decryptByPrivateKey(String data, String privateKeyString) throws Exception {
        byte[] encrypted  =Base64.decode(data,Base64.NO_WRAP);
        byte[] privateKey  =Base64.decode(privateKeyString,Base64.NO_WRAP);
        // 得到私钥对象
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory kf = KeyFactory.getInstance(RSA);
        PrivateKey keyPrivate = kf.generatePrivate(keySpec);
        // 解密数据
        Cipher cp = Cipher.getInstance(TRANSFORMATION);
        cp.init(Cipher.DECRYPT_MODE, keyPrivate);
        byte[] arr = cp.doFinal(encrypted);
        return new String(arr,"UTF8");
    }

    public KeyPair generateRSAKeyPair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(2048, new SecureRandom());
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
