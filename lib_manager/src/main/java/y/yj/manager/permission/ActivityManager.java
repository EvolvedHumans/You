package y.yj.manager.permission;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;

/**
 * 项目负责人： 杨帆
 * 包名：      y.yj.permission
 * 描述：      Activity 管理
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 06月 21日 11时 34分
 */
public class ActivityManager implements Application.ActivityLifecycleCallbacks {

    private final String TAG = this.getClass().getName();

    private static volatile ActivityManager sInstance;

    public static ActivityManager getInstance(){
        if (sInstance == null){
            synchronized (ActivityManager.class){
                if (sInstance == null){
                    sInstance = new ActivityManager();
                }
            }
        }
        return sInstance;
    }

    /**当前应用上下文对象*/
    private Application application;

    /**队列存放Activity，HashMap集合存放Activity，key值为Activity的hashcode，通过key值取出Activity对象*/

    public void init(Application application){
        this.application = application;
        //注册ActivityLifecycleCallbacks 监听
        this.application.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        Log.e(TAG,"onActivityCreated()");
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.d(TAG,"onActivityStarted()");
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.d(TAG,"onActivityResumed()");
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.d(TAG,"onActivityPaused()");
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.d(TAG,"onActivityStopped()");
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        Log.d(TAG,"onActivitySaveInstanceState()");
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.d(TAG,"onActivityDestroyed()");
    }
}
