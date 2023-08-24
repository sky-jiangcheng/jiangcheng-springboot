package com.jiangc.practice.encryption;

public class TestEncription {
    public static void main(String[] args) {
        String content = "hello,jiangcheng";
        String password = "123";

        System.out.println("加密前:"+content);

//        加密
        byte[] encrypt = AesEncryptor.encrypt(content,password);
        System.out.println("加密后:"+new String(encrypt));

        byte[] decrypt = AesEncryptor.decrypt(encrypt,password);
        System.out.println("解密后:"+new String(decrypt));
    }
}
