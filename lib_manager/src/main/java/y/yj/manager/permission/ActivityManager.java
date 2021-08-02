package y.yj.manager.permission;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.HashMap;

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

    public static ActivityManager getInstance() {
        if (sInstance == null) {
            synchronized (ActivityManager.class) {
                if (sInstance == null) {
                    sInstance = new ActivityManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * 当前应用上下文对象
     */
    private Application application;

    /**
     * 最后一个可见
     */
    private String mListVisible;

    /**
     * 最后一个不可见
     **/
    private String mListInvisible;

    /**
     * 队列存放Activity，HashMap集合存放Activity，key值为Activity的hashcode，通过key值取出Activity对象
     */
    private HashMap<String, Activity> hashMap = new HashMap<>();

    /**
     * HashMap集合key值获取
     * @param obj Activity类型
     * @return 唯一码
     */
    public String getKey(Object obj){
        return TAG + obj.hashCode();
    }

    /**
     * true 可见， false 不可见
     *
     * @return 判断是否前台可见
     */
    public boolean isForeground(){
        if (mListVisible.equals(mListInvisible)){
            return false;
        }
        return getTopActivity()!=null;
    }

    /**
     *
     * @return 获取栈顶Activity
     */
    public Activity getTopActivity(){
        return hashMap.get(mListVisible);
    }

    /**
     *
     * @param application 获取初始化Application
     */
    public void init(Application application) {
        this.application = application;
        //注册ActivityLifecycleCallbacks 监听
        this.application.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated()");
        mListVisible = getKey(activity);
        hashMap.put(getKey(activity),activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.d(TAG, "onActivityStarted()");
        mListVisible = getKey(activity);
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.d(TAG, "onActivityResumed()");
        mListVisible = getKey(activity);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.d(TAG, "onActivityPaused()");
        mListInvisible = getKey(activity);
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.d(TAG, "onActivityStopped()");
        mListInvisible = getKey(activity);
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.d(TAG, "onActivityDestroyed()");
        hashMap.remove(getKey(activity));
        mListInvisible = getKey(activity);
        if (getKey(activity).equals(mListInvisible)){
            //清除当前标记
            mListInvisible = null;
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        Log.d(TAG, "onActivitySaveInstanceState()");
    }
}
