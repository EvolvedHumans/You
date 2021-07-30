package y.yj.manager.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @name： 杨帆
 * @Time： 2021年 01月 14日 18时 43分
 * @Data： 加密工具类
 * @JDK: VERSION_1_8
 * @Android_SDK: VERSION_8.0
 */
public class CryptUtil {
    /**
     * MD5加密
     *
     * @param value
     * @return
     */
    public static String encryptMD5(String value) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(value.getBytes(StandardCharsets.UTF_8));
            byte[] md = md5.digest();
            return binToHex(md);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encryptMD5(byte[] value) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(value);
            byte[] md = md5.digest();
            return binToHex(md);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String binToHex(byte[] md) {
        StringBuilder sb = new StringBuilder();
        for (int b : md) {
            if (b < 0) {
                b += 256;
            }
            if (b < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(b));
        }
        return sb.toString();
    }

    /**
     * RandomAccessFile 获取文件的MD5值
     * 查看 RandomAccessFile 源码可得：
     * 构造方法中的参数mode的值，
     * "r"：可读
     * "w"：可写
     * "rw"：可读性
     * 未大文件进行加密计算
     *
     * @param file 文件路径
     * @return md5
     */
    public static String getFileMd5(File file) {
        MessageDigest messageDigest;
        RandomAccessFile randomAccessFile = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            if (file == null) {
                return "";
            }
            if (!file.exists()) {
                return "";
            }
            randomAccessFile=new RandomAccessFile(file,"r");
            byte[] bytes=new byte[1024*1024*10];
            int len=0;
            while ((len=randomAccessFile.read(bytes))!=-1){
                messageDigest.update(bytes,0, len);
            }
            BigInteger bigInt = new BigInteger(1, messageDigest.digest());
            String md5 = bigInt.toString(16);
            while (md5.length() < 32) {
                md5 = "0" + md5;
            }
            return md5;
        } catch (NoSuchAlgorithmException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                    randomAccessFile = null;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return "";
    }

}
