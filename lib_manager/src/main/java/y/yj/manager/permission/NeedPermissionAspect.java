package y.yj.manager.permission;

import android.app.Activity;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

import y.yj.manager.log.Log4j;
import y.yj.manager.permission.NeedPermission;

/**
 * 项目负责人： 杨帆
 * 包名：      y.yj.permission
 * 描述：      TODO 权限申请切面
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 06月 18日 14时 49分
 */
@Aspect
public class NeedPermissionAspect {

    private final String TAG = this.getClass().getSimpleName();

    private final String NeedPermission_METHOD = "execution(@y.yj.manager.permission.NeedPermission * *(..))";

    /**
     * 寻找AOP处理切点
     */
    @Pointcut(NeedPermission_METHOD)
    public void executionAop() {
    }

    @Around("executionAop() && @annotation(needPermission)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, NeedPermission needPermission) {

        //todo 判断当前Activity是否在前台，或者是否存在
        if (!ActivityManager.getInstance().isForeground()) {
            //拦截
            return;
        }
        //todo
        // 1.判断Activity是否为null
        // 2.判断Activity是否销毁
        // 3.判断Activity是否finishing
        Activity activity = ActivityManager.getInstance().getTopActivity();
        if (activity.isDestroyed() || activity.isFinishing()) {
            //拦截
            return;
        }

        Log4j.e(TAG,"拦截");

        XXPermissions.with(activity.getApplicationContext())
                .permission(Permission.CAMERA)
                .request(new OnPermissionCallback() {
                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        try {
                            joinPoint.proceed();
                        }catch (Throwable throwable){
                            throwable.printStackTrace();
                        }
                    }
                });

    }

}
