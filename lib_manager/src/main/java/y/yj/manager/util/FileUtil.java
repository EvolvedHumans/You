package y.yj.manager.util;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.FileUtils;
import android.webkit.MimeTypeMap;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import y.yj.manager.log.Log4j;

/**
 * @name： 杨帆
 * @Time： 2021年 01月 08日 14时 58分
 * @Data： Android SD卡、内部，各类型持久化存储辅助工具类
 * @JDK: VERSION_1_8
 * @Android_SDK: VERSION_8.0
 */
public class FileUtil {

    private final static String TAG = "com.yangf.pub_libs.util.FileUtil";

    //EXCEL文件后缀名
    public final static String EXCEL_SUFFIX = ".xls";

    /**
     * EXCEL表
     *
     * @param name 文件名
     * @return 完整EXCEL表文件名获取
     */
    public static String getExcelFileName(String name) {
        return name + EXCEL_SUFFIX;
    }

    /**
     * 判断该表是否为.xls格式的Excel表
     *
     * @param name 完整表名
     * @return true or false
     */
    public static boolean equipExcel(String name) {
        return name.endsWith(EXCEL_SUFFIX);
    }

    /**
     * 判断SD是否存在，获取最佳存储路径
     *
     * @param context 上下文
     * @return 缓存路径
     */
    public static String getCachePath(Context context) {
        String cachePath;
        //如果要存在SD卡中，需要先判断有无SD卡
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            //获取sd卡的Cache缓存路径
            cachePath = Objects.requireNonNull(context.getExternalCacheDir()).getPath();
        } else {
            //获取手机内部的Cache缓存路径
            cachePath = context.getCacheDir().getPath();
        }
        Log4j.d(TAG, cachePath);
        return cachePath;
    }

    /**
     * 设置导入文件目录位置Cache，SD卡或内部存储目录
     *
     * @param context 上下文
     * @param name    文件名
     * @return 返回的File类，Cache目录+文件名
     */
    public static File getCacheFile(Context context, String name) {
        String cachePath;
        //如果要存在SD卡中，需要先判断有无SD卡
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            //获取sd卡的Cache缓存路径
            cachePath = Objects.requireNonNull(context.getExternalCacheDir()).getPath();
        } else {
            //获取手机内部的Cache缓存路径
            cachePath = context.getCacheDir().getPath();
        }
        Log4j.d("存储路径", cachePath + File.separator + name);
        return new File(cachePath + File.separator + name);
    }

    /**
     * 判断该目录下是否有文件存在
     *
     * @param file 文件名
     * @return 存在true ,否则返回false
     */
    public static boolean isExistFile(File file) {
        return file.exists();
    }

    /**
     * 判断该目录是否存在，如果不存在则创建该目录
     *
     * @param path 路径
     * @return 存在true，是否返回false
     */
    public static boolean isExistCreateFile(String path) {
        File dir = new File(path);
        if (!dir.exists()) {//exists() 判断是否存在文件
            dir.mkdirs();  //dir.mkdirs(); //创建文件夹，如果父目录不存在，连同父目录一起创建
            return false;
        }
        return true;
    }

    /**
     * 删除该目录下对应文件
     *
     * @param file 所需删除的文件
     * @return 删除结果返回
     */
    public static boolean deleteFile(File file) {
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    /**
     * File与Uri转换
     *
     * @return 转化后所得的File
     */
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static File uriToFileApiQ(Context context, Uri uri) {
        File file = null;
        if (uri == null) return null;
        //android10以上转换
        if (Objects.equals(uri.getScheme(), ContentResolver.SCHEME_FILE)) {
            file = new File(Objects.requireNonNull(uri.getPath()));
        } else if (Objects.equals(uri.getScheme(), ContentResolver.SCHEME_CONTENT)) {
            //把文件复制到沙盒目录
            ContentResolver contentResolver = context.getContentResolver();
            String displayName = System.currentTimeMillis() + Math.round((Math.random() + 1) * 1000)
                    + "." + MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(uri));
            try {
                InputStream is = contentResolver.openInputStream(uri);
                File cache = new File(Objects.requireNonNull
                        (context.getExternalCacheDir()).getAbsolutePath(), displayName);
                FileOutputStream fos = new FileOutputStream(cache);
                assert is != null;
                FileUtils.copy(is, fos);
                file = cache;
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * File转Uri
     *
     * @return 文件地址转Uri
     */
    public static Uri fileToUri(Context context, String packageName, File file) {
        //测试此file为空对象
        if (file == null) {
            return null;
        }
        //测试此抽象路径名表示的文件或目录是否存在,如果存在则删除。
        if (file.exists()) {
            file.delete();
        }
        //createNewFile()；返回值为 boolean；
        //方法介绍：当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件
        //这里不判断了，创建失败也没办法
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //进行版本验证，Android7.0系统开始，直接使用本地真实路径的Uri会抛出异常，FileProvider是
        //一种特殊的内容提供其，可将封装过的Uri对外部进行共享，提高安全性
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            //这里使用了特殊的ContentProvider类似的机制来对数据进行保护，
            //可以选择性地将封装过的Uri共享给外部，就不会出错了
            uri = FileProvider.getUriForFile
                    (context, packageName, file);
        } else {
            //判断设备的系统版本，若低于Android7.0则将File对象转换成Uri对象，否则调用FileProvider的
            //getUriForFile方法将File对象转换成封装过得Uri对象
            //这里先不进行版本判断，只用第一种方法
            // 把文件地址转换成Uri格式
            //这种方法在Android7.0以上报错
            uri = Uri.fromFile(file);
        }
        return uri;
    }

}
