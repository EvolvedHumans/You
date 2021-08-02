package y.yj.manager.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.hjq.permissions.XXPermissions;

import y.yj.manager.BuildConfig;
import y.yj.manager.permission.ActivityManager;

/**
 * 项目负责人： 杨帆
 * 包名：      y.yj.manager.base
 * 描述：      TODO  Application管理父类
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 07月 18日 11时 18分
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //todo 非正式版打开日志功能
        if (!BuildConfig.isRelease){
            //打印Log
            ARouter.openLog();
            //开启调试模式
            ARouter.openDebug();
        }
        ARouter.init(this);
        XXPermissions.setScopedStorage(true);
        ActivityManager.getInstance().init(this);
    }
}











