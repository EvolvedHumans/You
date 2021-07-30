package y.yj.manager.util;

import android.annotation.SuppressLint;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public final static String CHARSET = "UTF-8";

    public static final String NUMBER = "0123456789";

    public static final Pattern PATTERN = Pattern.compile("[0-9]*");

    /**
     * 判断字符串是否为""或null
     */
    public static boolean isNullOrEmpty(String str) {
        return null == str || 0 == str.trim().length();
    }


    public static boolean isNullOrEmpty(int i) {
        return 0 == i;
    }

    public static boolean isNullOrEmpty(Integer integer) {
        return null == integer;
    }

    /**
     * 判断数组是否为""或null
     */
    public static boolean isNullOrEmpty(Object[] arr) {
        return null == arr || 0 == arr.length;
    }

    public static boolean isNullOrEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    public static boolean isNullOrEmpty(Collection col) {
        return null == col || col.isEmpty();
    }

    public static boolean isNullOrBlank(Object str) {
        return null == str || 0 == str.toString().trim().length();
    }

    /**
     * 判断是否为NULL
     *
     * @param str
     * @return
     */
    public static boolean isNull(Object str) {
        return null == str;
    }

    public static String toString(Object obj) {
        return StringUtil.isNull(obj) ? "null" : String.valueOf(obj);
    }


    /**
     * byte数组转String类型
     *
     * @param data   byte[] 数据源
     * @param length 长度
     * @return UTF-8格式String类型
     */
    public static String getString(byte[] data, int length) {
        try {
            return new String(data, 0, length, CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new String(data, 0, length, StandardCharsets.UTF_8);
    }

    public static String getString(byte[] data, int start, int length) {
        try {
            return new String(data, start, length, CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new String(data, start, length, StandardCharsets.UTF_8);
    }

    public static byte[] getBytes(String data) {
        if (null == data) {
            return new byte[0];
        }
        try {
            return data.getBytes(CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return data.getBytes(StandardCharsets.UTF_8);
    }

    public static boolean isValidIntBadge(int intBadge) {
        return (intBadge >= 0) && (intBadge <= 99999);
    }

    /**
     * 判断是否为NULL或者小于1
     *
     * @param str
     * @return
     */
    public static boolean isNullOrLessOne(Long str) {
        return isNull(str) || str < 1;
    }

    /**
     * 判断是否为NULL或者小于1
     *
     * @param str
     * @return
     */
    public static boolean isNullOrLessOne(Integer str) {
        return isNull(str) || str < 1;
    }

    /**
     * 判断是否为NULL或者小于1
     *
     * @param str
     * @return
     */
    public static boolean isNullOrLessOne(Short str) {
        return isNull(str) || str < 1;
    }

    /**
     * 判断是否为NULL或者小于零
     *
     * @param str
     * @return
     */
    public static boolean isNullOrLessZero(Long str) {
        return isNull(str) || str < 0;
    }

    /**
     * 判断是否为NULL或者小于零
     *
     * @param str
     * @return
     */
    public static boolean isNullOrLessZero(Integer str) {
        return isNull(str) || str < 0;
    }

    /**
     * 判断是否为NULL或者小于零
     *
     * @param str
     * @return
     */
    public static boolean isNullOrLessZero(Short str) {
        return isNull(str) || str < 0;
    }

    /**
     * String转为int
     *
     * @param str
     * @return
     */
    public static int convertStringToInt(String str) {
        if (isNullOrEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String gbToUTF(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成19随机单号 纯数字
     *
     * @return
     */
    public static String getOrderNo() {
        String orderNo = "";
        String trandNo = String.valueOf((Math.random() * 9 + 1) * 1000000);
        @SuppressLint("SimpleDateFormat")
        String sdf = new SimpleDateFormat("yyyyMMddHHMMSS").format(new Date());
        orderNo = trandNo.substring(0, 4);
        orderNo = orderNo + sdf;
        return orderNo;
    }

    /**
     * 整数相除 保留一位小数的百分比
     *
     * @param a
     * @param b
     * @return
     */
    public static String division(long a, long b) {
        float num = (float) a * 100 / b;
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(num) + "%";
    }

    /**
     * 随机获取数字
     *
     * @param len
     * @return
     */
    public static String randNumber(int len) {
        return randSpecify(len, NUMBER);
    }

    /**
     * 随机获取指定字符串中的字符
     *
     * @param len
     * @return
     */
    public static String randSpecify(int len, String str) {
        if (isNullOrEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int k = new Random().nextInt(str.length());
            sb.append(str.charAt(k));
        }

        return sb.toString();
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Matcher isNum = PATTERN.matcher(str);
        return isNum.matches();
    }


    /**
     * 将一个整形化为十六进制，并以字符串的形式返回
     */
    private final static String[] HEX_ARRAY = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};

    public static String byteToHex(int n) {
        if (n < 0) {
            n = n + 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEX_ARRAY[d1] + HEX_ARRAY[d2];
    }

    public static int signV1(String str) {
        byte[] byteStr = str.getBytes();
        int len = byteStr.length;
        if (len == 0) {
            return 0;
        }
        int xOr = 0;
        for (byte b : byteStr) {
            xOr ^= b;
        }
        return xOr;
    }

    /**
     * 字符串转换成十六进制字符串
     *
     * @param str 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();
        int bit;

        for (byte b : bs) {
            bit = (b & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = b & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 字符串转换成固定长度十六进制字符串,不足前面补零
     *
     * @param str 待转换的ASCII字符串
     * @return String
     */
    public static String str2HexStrNoSpaceAndFixedLength(String str, int len) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] newBs = new byte[len];
        if (StringUtil.isNullOrEmpty(str)) {
            for (int j = 0; j < len; j++) {
                newBs[j] = 0x00;
            }
        } else if (str.length() > len) {
            str = str.substring(0, len / 2);
            newBs = str.getBytes();
        } else if (str.length() == len) {
            newBs = str.getBytes();
        } else {
            byte[] bs = str.getBytes();
            int addLen = len - bs.length;
            for (int j = 0; j < addLen; j++) {
                newBs[j] = 0x00;
            }
            if (len - addLen >= 0)
                System.arraycopy(bs, 0, newBs, addLen, len - addLen);
        }
        int bit;
        for (byte newB : newBs) {
            bit = (newB & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = newB & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * 字符串转换成固定长度十六进制字符串,不足后面补零
     *
     * @param str 待转换的ASCII字符串
     * @return String
     */
    public static String str2HexFixedLengthAppendZeroAfter(String str, int len) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] newBs = new byte[len];
        if (StringUtil.isNullOrEmpty(str)) {
            for (int j = 0; j < len; j++) {
                newBs[j] = 0x00;
            }
        } else if (str.length() > len) {
            str = str.substring(0, len / 2);
            newBs = str.getBytes();
        } else if (str.length() == len) {
            newBs = str.getBytes();
        } else {
            byte[] bs = str.getBytes();
            int addLen = len - bs.length;
            System.arraycopy(bs, 0, newBs, 0, bs.length);
            for (int j = addLen; j < len; j++) {
                newBs[j] = 0x00;
            }
        }
        int bit;
        for (byte newB : newBs) {
            bit = (newB & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = newB & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * 固定长度字符串转换成十六进制字符串，不足前面补零
     *
     * @param str 待转换的ASCII字符串
     * @return String
     */
    public static String str2HexStrNoSpace(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] bs = str.getBytes();
        int bit;

        for (byte b : bs) {
            bit = (b & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = b & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString().trim();
    }

    /**
     * 十六进制转换字符串
     *
     * @param hexStr str Byte字符串(Byte之间无分隔符 如:[616C6B])
     * @return String 对应的字符串
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;

        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

    /**
     * bytes转换成十六进制字符串
     *
     * @param b byte数组
     * @return String 每个Byte值之间空格分隔
     */
    public static String byte2HexStr(byte[] b) {
        String stmp = "";
        StringBuilder sb = new StringBuilder();
        for (byte value : b) {
            stmp = Integer.toHexString(value & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }

    public static String char2HexStr(char[] c) {
        String stmp = "";
        StringBuilder sb = new StringBuilder();
        for (char value : c) {
            stmp = Integer.toHexString(value & 0xFF);
            sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }

    /**
     * bytes字符串转换为Byte值
     *
     * @param src Byte字符串，每个Byte之间没有分隔符
     * @return byte[]
     */
    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = Byte.decode("0x" + src.substring(i * 2, m) + src.substring(m, n));
        }
        return ret;
    }

    /**
     * String的字符串转换成unicode的String
     *
     * @param strText 全角字符串
     * @return String 每个unicode之间无分隔符
     * @throws Exception
     */
    public static String strToUnicode(String strText) {
        char c;
        StringBuilder str = new StringBuilder();
        int intAsc;
        String strHex;
        for (int i = 0; i < strText.length(); i++) {
            c = strText.charAt(i);
            intAsc = c;
            strHex = Integer.toHexString(intAsc);
            if (intAsc > 128) {
                str.append("\\u").append(strHex);
            } else {
                // 低位在前面补00
                str.append("\\u00").append(strHex);
            }
        }
        return str.toString();
    }

    /**
     * unicode的String转换成String的字符串
     *
     * @param hex 16进制值字符串 （一个unicode为2byte）
     * @return String 全角字符串
     */
    public static String unicodeToString(String hex) {
        int t = hex.length() / 6;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = hex.substring(i * 6, (i + 1) * 6);
            // 高位需要补上00再转
            String s1 = s.substring(2, 4) + "00";
            // 低位直接转
            String s2 = s.substring(4);
            // 将16进制的string转为int
            int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
            // 将int转换为字符
            char[] chars = Character.toChars(n);
            str.append(new String(chars));
        }
        return str.toString();
    }

    public static char[] ToUnsignedChar(char[] signChar) {
        for (int i = 0; i < signChar.length; i++) {
            int x = ((byte) signChar[i]) >= 0 ? signChar[i] : ((byte) signChar[i]) + 256;
            signChar[i] = (char) x;
        }
        return signChar;
    }

    // 使用ArrayList方法
    //java 合并两个byte数组
    public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
        byte[] bt3 = new byte[bt1.length + bt2.length];
        int i = 0;
        for (byte bt : bt1) {
            bt3[i] = bt;
            i++;
        }

        for (byte bt : bt2) {
            bt3[i] = bt;
            i++;
        }
        return bt3;
    }

    public static String getBCC(byte[] data) {
        String ret = "";
        byte[] BCC = new byte[1];
        for (byte datum : data) {
            BCC[0] = (byte) (BCC[0] ^ datum);
        }
        String hex = Integer.toHexString(BCC[0] & 0xFF);
        if (hex.length() == 1) {
            hex = '0' + hex;
        }
        ret += hex.toUpperCase();
        return ret;
    }

    /**
     * 字符以，分割返回字符数组
     *
     * @param str
     * @return
     */
    public static String[] strToArray(String str) {
        StringTokenizer st = new StringTokenizer(str, ",");
        String[] strArray = new String[st.countTokens()];
        int strLeng = st.countTokens();
        for (int i = 0; i < strLeng; i++) {
            strArray[i] = st.nextToken();
        }
        return strArray;
    }

    public static boolean isContainArray(Integer[] array, int digit) {
        boolean contain = false;
        for (Integer i : array) {
            if (i == digit) {
                contain = true;
                break;
            }
        }
        return contain;
    }

    /**
     * Hex字符串转byte
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte
     */
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

    /**
     * hex字符串转byte数组
     *
     * @param inHex 待转换的Hex字符串
     * @return 转换后的byte数组结果
     */
    public static byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {
            //奇数
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else {
            //偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String URLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取固定长度字符串，不足前面补0，超过截取后后面固定长度
     *
     * @param str
     * @param len
     * @return
     */
    public static String getFixLenStrWithHeadAppendZeroAndSubAfter(String str, int len) {
        if (StringUtil.isNullOrEmpty(str)) {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < len; i++) {
                strBuilder.append("0");
            }
            str = strBuilder.toString();
            return str;
        }
        if (str.length() > len) {
            return str.substring(str.length() - len);
        } else if (str.length() == len) {
            return str;
        } else {
            str.length();
            return str;
        }
    }
}