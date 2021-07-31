package y.yj.manager.permission;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 项目负责人： 杨帆
 * 包名：      y.yj.permission
 * 描述：      TODO 所需要权限的判断
 * 编译环境：  JDK-1_8、SDK-8.0
 * 创建时间：  2021年 06月 18日 17时 30分
 */
@Aspect
public class IsPermissionAspect {

    private final String TAG = this.getClass().getSimpleName();

    private final String IsPermission_METHOD = "execution(@y.yj.permission.IsPermission * *(..))";
    

    /**
     * 寻找AOP处理切口
     */
    @Pointcut()
    public void executionAop() { }

}
