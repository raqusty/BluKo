package com.raqust.bluko.common.utils;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by linzehao
 * time: 2018/1/24.
 * info:加密算法
 */

public class RSAEncryptUtil {

    public static final String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2TON2Tdfq+VOTrE8Dg4SECaB/JxDUTRkGnTWkB8xLz/+9rwFl265c+Kpw+K6blEmiHXXWgV1BzuNiCIhDHYG8Mr3a/A9onx4xRDujtDyBK1YssErHsybDPpJGH5+IVc0Luo82Wx43RuLQyiYLt3inTbMaZBak9vnwx/ZbsVT8p9/igDFFV1HvxW83NJdt1fYf54g42ePFOVzsKlnJUbIDv6wPsmtMI+DzcGQNAQSv7V5zGZgsNsE4gIWoyPI0ML+8jCFDkudMLEHpscHfdYADKOha3NhDhHe4qDvL2a3vX9p0vLhw2y9WlS9YDYdsQU0+JAjq0QqLs8FHil3DBQT0QIDAQAB";
    public static final String privateKeyStr = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDZM43ZN1+r5U5OsTwODhIQJoH8nENRNGQadNaQHzEvP/72vAWXbrlz4qnD4rpuUSaIdddaBXUHO42IIiEMdgbwyvdr8D2ifHjFEO6O0PIErViywSsezJsM+kkYfn4hVzQu6jzZbHjdG4tDKJgu3eKdNsxpkFqT2+fDH9luxVPyn3+KAMUVXUe/Fbzc0l23V9h/niDjZ48U5XOwqWclRsgO/rA+ya0wj4PNwZA0BBK/tXnMZmCw2wTiAhajI8jQwv7yMIUOS50wsQemxwd91gAMo6Frc2EOEd7ioO8vZre9f2nS8uHDbL1aVL1gNh2xBTT4kCOrRCouzwUeKXcMFBPRAgMBAAECggEBAIwPf5gj6hC51I5QIe7aSfGaM1ZHGe3CgA7Dax0S0C+s7+gBhQWKxBIjht+nVBsRP2zduJn3TOj2ESVJBNC5R259I6G6ELS32VUSvxLfUEGN2tWiVQEOZamvt/UQlJtBVYgvSj/Wf/Xs7hfHlKMcOCazEIu+J3qTYtdjsV2J58FawkCLs4WTO1jFbtaXIwl33EndAweZImSttunfoPg+zDjZOybPbvugcA6NvMtmdBeQcVACzhYMhGdn/Lub1rHBTMKxbd/TJPzRAnUA/On/rftnGfacmOu13Hg+bDBX1wsKh63wd2o4I+5RCr/B2KLW82ORMR5D2GlYGOBoJJtVrUECgYEA+pfJtvHm/Nz+BK3nxl2pWjcNFicVDupJhBJvPeygUczaHxy+2w8xXCGiKpgngQaeUCj4wX1TyLj6YSs4EoZe7Rkm1JtWOS0okm3hdOJbNBH0hRwg+/bo3ks/vvxTry7svx9Zr96vU2ZHN2vMtBRXpUDNT06Zd8ET1dYwiPt502kCgYEA3eNR2Pmz2B2wSEf7m/f2DA0PnbOIHdEEQyq61NJTptxP2sAsFyNaJZIHHCKr/xe/0BkszhaDyRWpX4N8v0ngQ/TOAaMwYwCejem1jfxIUahhBRlQpBmGIXQBAlz01X+I1LnvDAqgdSnFkJATg3MWyuQtiTVtCFq+XRReHB+4eCkCgYBuJxq8GEl5DYt7cxZW1AUFav2Np1BSZho6u/+6MLGoQ2v+ERy0HlGMNhyarJdw1//vLJNsOjMCII2u+NiFDBveDRhMjJuyNm+HLlXUk0uT7/CUMnEtULO8Q0eaJECE2ROoT5eU/0YCWwsjCXfKEMcWFQ4qtlovkeeuL6DWFfTPQQKBgQCC6T0+8IPk/A8ndiDCspceIQ6Xkna6cLk9D5bPPZBRICaU/1CEDj1/cRp0xRgJFu/6TQAcTzhNiVQ2oBMXoPSJ44MvgCJqJtFlFQAi1zerxdYH9hmX199FGXYG+OUSmX4XU+PvjM2CkrSXSAnbQimuZtVe4ICFr1QlAoFLwoNJkQKBgQDcSb/tbR7bLzIgpgRaA68W/K5zSUI90695qAIMrHaXwGxQuEZvTrVBm9N0yHd3mQo24KJmxLGHGyr6yh/aYPiAA+A39ktHuyYHjXdE6FLAu3UE3pbna11/EeYF5FkLd0lm1YBzrJbVSnjejSlgKgAuS9SAnvIBPCnYTAFCxNBdsw==";
    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = null;
        keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(2048, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        try {
            // 得到公钥字符串
            String publicKeyString = Base64.encodeToString(publicKey.getEncoded(),Base64.NO_WRAP);
            // 得到私钥字符串
            String privateKeyString = Base64.encodeToString(privateKey.getEncoded(),Base64.NO_WRAP);
            // 将密钥对写入到文件
            System.out.println("public key = ");
            System.out.println(publicKeyString);

            System.out.println("private key = ");
            System.out.println(privateKeyString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static RSAPublicKey decodeBase64PublicKey(String publicKey) {
        byte[] buffer = Base64.decode(publicKey,Base64.NO_WRAP);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException("sso 生成失败", e);
        }
    }

    public static RSAPrivateKey decodeBase64PrivateKey(String privateKey) {
        byte[] buffer2 = Base64.decode(privateKey,Base64.NO_WRAP);
        PKCS8EncodedKeySpec keySpec2 = new PKCS8EncodedKeySpec(buffer2);
        try {
            KeyFactory keyFactory2 = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory2.generatePrivate(keySpec2);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("sso 生成失败", e);
        }
    }

    public static void main(String[] args) {
        /*String device = encryptByPublicKey(RSAKey.publicKeyString, "123");
		System.out.println("加密后：" + device);
		String decrypt = decryptByPrivateKey(RSAKey.privateKeyString, device);
		System.out.println("解密后：" + decrypt);*/
        //byte[] encrypt = encrypt(decodeBase64PublicKey(RSAKey.publicKeyString),"123455sdcsdcasdcasdcsdsfsfadasdfasffffffffffffffffffffffdsfhlskhffffffffffffffffffffffffff5".getBytes());

    }

    /**
     * 公钥加密
     *
     * @param publicKey 公钥
     * @param text      需要加密的数据
     * @return
     */
    public static String encryptByPublicKey(String publicKey, String text) {
        byte[] encrypt = encrypt(decodeBase64PublicKey(publicKey), text.getBytes());
        return Base64.encodeToString(encrypt,Base64.NO_WRAP);
    }

    /**
     * 公钥加密过程
     *
     * @param publicKey     公钥
     * @param plainTextData 明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws RuntimeException {
        if (publicKey == null) {
            throw new RuntimeException("加密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此加密算法", e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("加密公钥非法,请检查", e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("明文长度非法", e);
        } catch (BadPaddingException e) {
            throw new RuntimeException("明文数据已损坏", e);
        }
    }

    /**
     * 私钥加密
     *
     * @param privateKey
     * @param plainTextData
     * @return
     * @throws RuntimeException
     */
    public static String encryptAndBase64(RSAPrivateKey privateKey, String plainTextData)
            throws RuntimeException {
        return Base64.encodeToString(encrypt(privateKey, plainTextData.getBytes()),Base64.NO_WRAP);
    }


    public static byte[] encrypt(RSAPrivateKey privateKey, String plainTextData) throws RuntimeException {
        return encrypt(privateKey, plainTextData.getBytes());
    }

    /**
     * 私钥加密过程
     *
     * @param privateKey    私钥
     * @param plainTextData 明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData) throws RuntimeException {
        if (privateKey == null) {
            throw new RuntimeException("加密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此加密算法", e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("加密私钥非法,请检查", e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("明文长度非法", e);
        } catch (BadPaddingException e) {
            throw new RuntimeException("明文数据已损坏", e);
        }
    }

    /**
     * 私钥解密
     *
     * @param privateKeyStr base64加密后的私钥
     * @param cipherDataStr 密文数据
     * @return
     */
    public static String decryptByPrivateKey(String privateKeyStr, String cipherDataStr) {
        byte[] encryptByBase64 = Base64.decode(cipherDataStr,Base64.NO_WRAP);
        byte[] decrypt = decrypt(decodeBase64PrivateKey(privateKeyStr), encryptByBase64);
        return new String(decrypt);
    }

    /**
     * 私钥解密过程
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws RuntimeException {
        if (privateKey == null) {
            throw new RuntimeException("解密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此解密算法", e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("解密私钥非法,请检查", e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("密文长度非法", e);
        } catch (BadPaddingException e) {
            throw new RuntimeException("密文数据已损坏", e);
        }
    }

    /**
     * 公钥解密过程
     *
     * @param publicKey  公钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData) throws RuntimeException {
        if (publicKey == null) {
            throw new RuntimeException("解密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA");
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("无此解密算法", e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("解密公钥非法,请检查", e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException("密文长度非法", e);
        } catch (BadPaddingException e) {
            throw new RuntimeException("密文数据已损坏", e);
        }
    }
}
