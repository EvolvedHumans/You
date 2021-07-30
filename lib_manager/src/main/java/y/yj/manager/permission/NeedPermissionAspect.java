package y.yj.manager.permission;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

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

    private final String NeedPermission_METHOD = "execution(@y.yj.permission.NeedPermission * *(..))";

    /**
     * 寻找AOP处理切点
     */
    @Pointcut(NeedPermission_METHOD)
    public void executionAop() {
    }

    @Around("executionAop() && @annotation(needPermission)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, NeedPermission needPermission) {
//        XXPermissions.with()
//                .permission(needPermission.value())
//                .request(new OnPermissionCallback() {
//                    @Override
//                    public void onGranted(List<String> permissions, boolean all) {
//
//                    }
//                });
    }

}
