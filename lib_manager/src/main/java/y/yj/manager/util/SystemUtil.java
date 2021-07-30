package y.yj.manager.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * 项目负责人： 杨帆
 * 包名：      com.yangf.pub_libs.util
 * 描述：      TODO 系统应用信息获取
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 02月 22日 18时 32分
 */
public class SystemUtil {

    private final static String TAG = "com.yangf.pub_libs.util.SystemUtil";

    /**
     * @return 检查系统蓝牙是否支持BLE协议
     */
    public static boolean getBlueUsed(Context context) {
        boolean isBleUsed;
        try {
            return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param mContext 上下文
     * @return 本机APP应用的包名
     */
    public static String getPacakgeName(Context mContext) {
        try {
            ActivityManager activityManager = (ActivityManager) mContext
                    .getSystemService(Activity.ACTIVITY_SERVICE);
            ComponentName componentName = activityManager.getRunningTasks(1).get(0).topActivity;
            return componentName.getPackageName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param mContext   上下文
     * @param permission 权限
     * @return 判断应用是否缺少权限
     */
    public static boolean isPermission(Context mContext, String permission) {
        return !(ContextCompat.checkSelfPermission(mContext, permission) == PackageManager.PERMISSION_DENIED);
    }

    /**
     * @param mContext         上下文
     * @param permissionMuster 权限组
     * @return 如果有一个权限未给到，则返回false出去，全部授权完成则是true
     */
    public static boolean isPermissionMuster(Context mContext, List<String> permissionMuster) {
        for (String permission : permissionMuster) {
            if (!isPermission(mContext, permission)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取程序版本号,源码如下：
     *      *
     *      * @deprecated Use  instead, which includes both
     *      * this and the additional attribute.
     *      * The version number of this package, as specified by the &lt;manifest&gt;
     *      * tag's attribute.
     *      * @see #getLongVersionCode()
     *      以上代码是API-28的时候被加入的，因此在API-28以上调用versionCode会导致程序崩溃不可用
     */
    public static long getVersionCode(Context context){
        long version = 0;
        PackageManager packageManager = context.getPackageManager();
        //todo 版本号小于API-28
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P){
            try {
                //todo 获取版本号
                version = packageManager.getPackageInfo(context.getPackageName(),0).versionCode;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else { //todo 版本号大于或等于API-28
            try {
                version = packageManager.getPackageInfo(context.getPackageName(),0).getLongVersionCode();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return version;
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 获得设备序列号（如：WTK7N16923005607）, 个别设备无法获取
     * 正在用
     * @return 设备序列号
     */
    @SuppressLint("HardwareIds")
    public static String getSERIAL() {
        try {
            return Build.SERIAL;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 可用内存
     * @param context 上下文
     * @return 获取系统当前可用内存
     */
    public static long getAvailableMemory(Context context){
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem/(1024*1024);
    }

}
