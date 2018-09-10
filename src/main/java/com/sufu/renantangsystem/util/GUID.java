package com.sufu.renantangsystem.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class GUID {

    private static final Logger logger = LoggerFactory.getLogger(GUID.class);

    public String valueBeforeMD5 = "";
    public String valueAfterMD5 = "";
    private static Random myRand;
    private static SecureRandom mySecureRand = new SecureRandom();
    private static String s_id;

    static {
        long l = mySecureRand.nextLong();
        myRand = new Random(l);
        try {
            s_id = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException localUnknownHostException) {
            logger.error("系统运行异常UnknownHostException" + localUnknownHostException);
        }
    }

    public GUID() {
        getRandomGUID(false);
    }

    public GUID(boolean secure) {
        getRandomGUID(secure);
    }

    private void getRandomGUID(boolean secure) {
        MessageDigest md5 = null;
        StringBuffer sbValueBeforeMD5 = new StringBuffer();
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error: " + e);
        }
        try {
            long time = System.currentTimeMillis();
            long rand = 0L;
            if (secure) {
                rand = mySecureRand.nextLong();
            } else {
                rand = myRand.nextLong();
            }
            sbValueBeforeMD5.append(s_id);
            sbValueBeforeMD5.append(":");
            sbValueBeforeMD5.append(Long.toString(time));
            sbValueBeforeMD5.append(":");
            sbValueBeforeMD5.append(Long.toString(rand));
            this.valueBeforeMD5 = sbValueBeforeMD5.toString();
            md5.update(this.valueBeforeMD5.getBytes());
            byte[] array = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < array.length; ++j) {
                int b = array[j] & 0xFF;
                if (b < 16) {
                    sb.append('0');
                }
                sb.append(Integer.toHexString(b));
            }
            this.valueAfterMD5 = sb.toString();
        } catch (Exception e) {
            System.out.println("Error:" + e);
        }
    }

    @Override
    public String toString() {
        String raw = this.valueAfterMD5.toUpperCase();
        StringBuffer sb = new StringBuffer();
        sb.append(raw.substring(0, 8));
        sb.append(raw.substring(8, 12));
        sb.append(raw.substring(12, 16));
        sb.append(raw.substring(16, 20));
        sb.append(raw.substring(20));
        return sb.toString();
    }

    public static String getID() {
        GUID guid = new GUID();
        return guid.toString();
    }

}