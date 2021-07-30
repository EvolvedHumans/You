package y.yj.module_login;

import com.alibaba.android.arouter.facade.annotation.Route;

import y.yj.lib_weight.AdertAlertDialog;
import y.yj.manager.argument.ArguActivity;
import y.yj.manager.base.BaseActivity;

@Route(path = ArguActivity.ARouterLoginActivity)
public class LoginActivity extends BaseActivity {

    @Override
    public int layoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        AdertAlertDialog.init(this);
    }
}